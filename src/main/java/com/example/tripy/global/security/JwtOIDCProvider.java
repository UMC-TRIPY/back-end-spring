package com.example.tripy.global.security;

import com.example.tripy.domain.auth.dto.AuthResponseDto.OIDCDecodePayload;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Configuration
@RequiredArgsConstructor
@Service
@Slf4j
public class JwtOIDCProvider {

    //header, body 받아오는 로직
    private String getUnsignedToken(String token){
        String[] splitToken = token.split("\\.");
        if(splitToken.length != 3)
            throw new GeneralException(ErrorStatus._INVALID_JWT);
        return splitToken[0] + "." + splitToken[1] + ".";
    }

    //인증되지 않은 IdToken에서 payload 받아오는 로직
    private Jwt<Header, Claims> getUnsignedTokenClaims(String token, String iss, String aud) {
        try {
            return Jwts.parserBuilder()
                .requireAudience(aud) //aud(카카오톡 어플리케이션 아이디) 가 같은지 확인
                .requireIssuer(iss)//iss(이슈어)가 카카오인지 확인
                .build()
                .parseClaimsJwt(getUnsignedToken(token));
        } catch (ExpiredJwtException e) { //파싱하면서 만료된 토큰인지 확인.
            throw new GeneralException(ErrorStatus._EXPIRED_JWT);
        } catch (Exception e) {
            log.error(e.toString());
            throw new GeneralException(ErrorStatus._INVALID_JWT);
        }
    }

    private final String KID = "kid";
    public String getKidFromUnsignedTokenHeader(String token, String iss, String aud){
        return (String) getUnsignedTokenClaims(token, iss, aud).getHeader().get(KID);
    }

    //공개키로 토큰 검증
    public Jws<Claims> getOIDCTokenJws(String token, String modulus, String exponent){
        try{
            return Jwts.parserBuilder()
                .setSigningKey(getRSAPublicKey(modulus, exponent))
                .build()
                .parseClaimsJws(token);
        } catch (ExpiredJwtException e){
            throw new GeneralException(ErrorStatus._EXPIRED_JWT);
        } catch (Exception e){
            log.error(e.toString());
            throw new GeneralException(ErrorStatus._INVALID_JWT);
        }
    }

    //OIDCDecodePayload를 가져옴. OIDC 스펙 -> 공통 사용
    public OIDCDecodePayload getOIDCTokenBody(String token, String modulus, String exponent){
        Claims body = getOIDCTokenJws(token, modulus, exponent).getBody();
        return new OIDCDecodePayload(
            body.getIssuer(),
            body.getAudience(),
            body.getSubject(),
            body.get("email", String.class),
            body.get("nickname", String.class),
            body.get("picture", String.class)
        );
    }

    //n, e 값으로 RSA 퍼블릭 키 연산
    private Key getRSAPublicKey(String modulus, String exponent)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder().decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder().decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }


}
