package com.example.tripy.domain.auth;


import com.example.tripy.domain.auth.dto.AuthResponseDto.LoginSimpleInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name="Auth", description = "인증(로그인, 회원가입) 관련")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인/회원가입 api", description = "code : Authorization code / 회원가입, 로그인 구분 없이 동일한 API 사용")
    public LoginSimpleInfo getIdToken(@RequestParam String code){
        String idToken = authService.getOauth2Authentication(code);
        return authService.login(idToken);
    }


}
