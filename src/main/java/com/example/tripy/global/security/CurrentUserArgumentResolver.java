package com.example.tripy.global.security;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.member.MemberRepository;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null
            && parameter.getParameterType().equals(Member.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        String authorizationHeader = webRequest.getHeader("authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // "Bearer " 이후 문자열
            return loadUserFromToken(token);
        }
        throw new GeneralException(ErrorStatus._EMPTY_JWT);
    }

    public Member loadUserFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtTokenProvider.getSecretKey())
                .parseClaimsJws(token).getBody();

            String targetIndex = "+";

            String targetSubject = claims.getSubject();

            int index = targetSubject.indexOf(targetIndex);
            String userId = targetSubject.substring(0, index);

            return memberRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_MEMBER));
        } catch (Exception ex) {
            throw new GeneralException(ErrorStatus._INVALID_JWT);
        }
    }
}
