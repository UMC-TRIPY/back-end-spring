package com.example.tripy.domain.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final KakaoAuthService kakaoAuthService;

    @PostMapping("/token")
    public String getIdToken(@RequestParam String code){
        return kakaoAuthService.getOuth2Authentication(code);

    }
    @PostMapping("/caching")
    public void updateOpenKey(){
        kakaoAuthService.updateOpenKeyTestRedis();
    }

}
