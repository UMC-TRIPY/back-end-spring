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

    //key값을 잘 캐싱하는지 확인하기 위한 api(배포 시 삭제 예정)
    @PostMapping("/caching")
    public void updateOpenKey(){
        authService.updateOpenKeyTestRedis();
    }

}
