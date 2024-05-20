package com.example.tripy.domain.auth;


import com.example.tripy.domain.auth.dto.AuthResponseDto.LoginSimpleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginSimpleInfo getIdToken(@RequestParam String code){
        String idToken = authService.getOauth2Authentication(code);
        return authService.login(idToken);
    }


}
