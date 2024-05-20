package com.example.tripy.domain.auth;


import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCDecodePayload;
import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCPublicKeysResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KakaoOauthHelper {
    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final OauthOIDCHelper oauthOIDCHelper;
    @Value("${security.oauth2.client.kakao.client-id}")
    private String appId;

    @Value("${security.oauth2.client.kakao.iss}")
    private String iss;

    public OIDCDecodePayload getOIDCDecodePayload(String token){
        OIDCPublicKeysResponse oidcPublicKeysResponse = kakaoAuthApiClient.getKakaoOIDCOpenKeys();
        return oauthOIDCHelper.getPayloadFromIdToken(
            token, iss, appId, oidcPublicKeysResponse
        );
    }



}
