package com.example.tripy.domain.auth;

import com.example.tripy.domain.auth.dto.AuthResponseDto.KakaoAccessTokenResponse;
import com.example.tripy.domain.auth.dto.AuthResponseDto.LoginSimpleInfo;
import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCDecodePayload;
import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCPublicKeysResponse;
import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.member.MemberRepository;
import com.example.tripy.domain.member.enums.SocialType;
import com.example.tripy.global.security.JwtTokenProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class AuthService {
      private final KakaoAuthApiClient kakaoAuthApiClient;
      private final RedisTemplate<String, Object> redisTemplate;
      private final KakaoOauthHelper kakaoOauthHelper;
      private final MemberRepository memberRepository;
      private final JwtTokenProvider jwtTokenProvider;


      @Value("${security.oauth2.client.kakao.authorization-grant-type}")
      private String grantType;
      @Value("${security.oauth2.client.kakao.client-id}")
      private String clientId;
      @Value("${security.oauth2.client.kakao.redirect-uri}")
      private String redirectUri;




    public String getOauth2Authentication(final String authorizationCode){
        KakaoAccessTokenResponse tokenInfo = kakaoAuthApiClient.getOAuth2AccessToken(
            grantType,
            clientId,
            redirectUri,
            authorizationCode
        );
        return tokenInfo.idToken();
    }

    @Transactional
    public LoginSimpleInfo login(String idToken){
        OIDCDecodePayload oidcDecodePayload = kakaoOauthHelper.getOIDCDecodePayload(idToken);
        String email = oidcDecodePayload.email();
        String nickName = oidcDecodePayload.nickName();
        String picture = oidcDecodePayload.picture();
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        String accessToken = null;
        String refreshToken = null;
        Member member = null;

        //회원가입
        if(optionalMember.isEmpty()){
            member = Member.toEntity(email, nickName, picture, SocialType.KAKAO);
            memberRepository.save(member);
            accessToken = jwtTokenProvider.createAccessToken(member.getPayload());
            refreshToken = jwtTokenProvider.createRefreshToken(member.getId());
        }
        if(optionalMember.isPresent()){
            accessToken = jwtTokenProvider.createAccessToken(optionalMember.get().getPayload());
            refreshToken = jwtTokenProvider.createRefreshToken(optionalMember.get().getId());
            member = optionalMember.get();

        }

        member.updateToken(accessToken, refreshToken);

        return LoginSimpleInfo.toDTO(accessToken, refreshToken);


    }

    @Cacheable(cacheNames = "KakaoOIDC", cacheManager = "oidcCacheManager")
    public OIDCPublicKeysResponse getKakaoOIDCOpenKeys() {
        return kakaoAuthApiClient.getKakaoOIDCOpenKeys();
    }

    public void updateOpenKeyTestRedis() {
        OIDCPublicKeysResponse oidcPublicKeysResponse = getKakaoOIDCOpenKeys();
        saveOIDCPublicKeysResponse(oidcPublicKeysResponse);
    }
    public void saveOIDCPublicKeysResponse(OIDCPublicKeysResponse response) {
        String key = "oidc:public_keys";

        redisTemplate.opsForValue().set(key, response);
    }


}
