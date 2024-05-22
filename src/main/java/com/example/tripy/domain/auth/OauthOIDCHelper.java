package com.example.tripy.domain.auth;

import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCDecodePayload;
import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCPublicKey;
import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCPublicKeysResponse;
import com.example.tripy.global.security.JwtOIDCProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class OauthOIDCHelper {

    private final JwtOIDCProvider jwtOIDCProvider;

    //토큰에서 kid 가져온다 -> 가져온 kid는 공개키 결정에 사용
    private String getKidFromUnsignedIdToken(String token, String iss, String aud){
        return jwtOIDCProvider.getKidFromUnsignedTokenHeader(token, iss, aud);
    }

   public OIDCDecodePayload getPayloadFromIdToken(String token, String iss, String aud, OIDCPublicKeysResponse oidcPublicKeysResponse){
        String kid = getKidFromUnsignedIdToken(token, iss, aud);

        //같은 Kid인 공개키 불러와서 토큰 검증에 사용
        OIDCPublicKey oidcPublicKey = oidcPublicKeysResponse.keys().stream()
            .filter(o-> o.kid().equals(kid))
            .findFirst()
            .orElseThrow();

        //검증 된 토큰에서 바디를 꺼내온다
        return jwtOIDCProvider.getOIDCTokenBody(token, oidcPublicKey.n(), oidcPublicKey.e());
   }

}
