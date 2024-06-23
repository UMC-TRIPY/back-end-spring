package com.example.tripy.global.security;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.member.MemberRepository;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application-dev.yml")
@Service
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.access-token.expire-length}")
    private long accessTokenValidityInMilliseconds;
    @Value("${jwt.refresh-token.expire-length}")
    private long refreshTokenValidityInMillseconds;
    @Value("${jwt.custom.secretKey}")
    private String SECRET_KEY;


    private SecretKey cachedSecretKey;
    private final MemberRepository memberRepository;

    private SecretKey _getSecretKey() {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        return Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
    }


    // 시크릿 키를 반환하는 method
    public SecretKey getSecretKey() {
        if (cachedSecretKey == null)
            cachedSecretKey = _getSecretKey();

         return cachedSecretKey;
    }

    public String createAccessToken(String payload) {
        return createToken(payload, accessTokenValidityInMilliseconds);
    }

    public String createRefreshToken(Long climberId) {
        return createToken(climberId.toString(), refreshTokenValidityInMillseconds);
    }

    public String refreshAccessToken(String refreshToken) {
        if (validateToken(refreshToken)) {
            Long userId = Long.parseLong(getPayload(refreshToken));
            Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._INVALID_MEMBER));
            String payload = member.getPayload();
            return createAccessToken(payload);

        } else {
            throw new GeneralException(ErrorStatus._INVALID_JWT);
        }
    }



    public String createToken(String payload, long expireLength){
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        long nowMillis = now.getTime();
        long expireMillis = nowMillis + (expireLength * 1000); // expireLength를 초 단위로 받았다고 가정
        Date validity = new Date(expireMillis);
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, getSecretKey())
            .compact();
    }

    public String getPayload(String token){
        try{
            return Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        }catch (ExpiredJwtException e){
            return e.getClaims().getSubject();
        }catch(JwtException e){
            log.error(e.toString());
            throw new GeneralException(ErrorStatus._INVALID_JWT);
        }
    }
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token);
            Date expiration = claimsJws.getBody().getExpiration();
            if (expiration != null && expiration.before(new Date())) {
                throw new GeneralException(ErrorStatus._EXPIRED_JWT);
            }
            return true;
        } catch (ExpiredJwtException e) {
            log.error(e.toString());
            throw new GeneralException(ErrorStatus._EXPIRED_JWT);
        } catch (JwtException | IllegalArgumentException exception) {
            throw new GeneralException(ErrorStatus._INVALID_JWT);
        }
    }


}
