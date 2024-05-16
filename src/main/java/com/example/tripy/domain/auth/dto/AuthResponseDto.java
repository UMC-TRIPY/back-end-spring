package com.example.tripy.domain.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class AuthResponseDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginSimpleInfo{
        private String accessToken;
        private String refreshToken;

        public static LoginSimpleInfo toDTO(String accessToken, String refreshToken) {
            return LoginSimpleInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        }

    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record KakaoAccessTokenResponse(
        String idToken
    ) {
        public static KakaoAccessTokenResponse of(
            final String idToken
        ) {
            return new KakaoAccessTokenResponse(
                idToken
            );
        }



    }
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record OIDCPublicKeysResponse(List<Key> keys) {
    }
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Key(
        String kid,
        String kty,
        String alg,
        String use,
        String n,
        String e
    ) {
    }
//    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
//    public record OIDCPublicKeysResponse(
//        String issuer,
//        String authorization_endpoint,
//        String token_endpoint,
//        String userinfo_endpoint,
//        String jwks_uri,
//        List<String> token_endpoint_auth_methods_supported,
//        List<String> subject_types_supported,
//        List<String> id_token_signing_alg_values_supported,
//        Boolean request_uri_parameter_supported,
//        List<String> response_types_supported,
//        List<String> response_modes_supported,
//        List<String> grant_types_supported,
//        List<String> code_challenge_methods_supported,
//        List<String> claims_supported
//    ) {}



}
