package com.example.tripy.domain.auth;

import com.example.tripy.domain.auth.dto.AuthResponseDto.KakaoAccessTokenResponse;
import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCPublicKeysResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class KakaoAuthService {
      private final KakaoAuthApiClient kakaoAuthApiClient;
      private final RedisTemplate<String, Object> redisTemplate;

      @Value("${security.oauth2.client.registration.kakao.authorization-grant-type}")
      private String grantType;
      @Value("${security.oauth2.client.registration.kakao.client-id}")
      private String clientId;
      @Value("${security.oauth2.client.registration.kakao.redirect-uri}")
      private String redirectUri;

    public String getOuth2Authentication(final String authorizationCode){
        KakaoAccessTokenResponse tokenInfo = kakaoAuthApiClient.getOAuth2AccessToken(
            grantType,
            clientId,
            redirectUri,
            authorizationCode
        );
        return tokenInfo.idToken();
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
