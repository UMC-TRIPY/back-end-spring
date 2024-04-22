package com.example.tripy.domain.auth;

import com.example.tripy.domain.auth.dto.AuthResponseDto.KakaoAccessTokenResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class KakaoAuthService {
      private final KakaoAuthApiClient kakaoAuthApiClient;

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


}
