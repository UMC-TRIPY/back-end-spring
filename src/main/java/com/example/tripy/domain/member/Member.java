package com.example.tripy.domain.member;

import com.example.tripy.domain.member.enums.SocialType;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nickName;

    @NotNull
    private String email;


    private String profileImgUrl;

    @NotNull
    private SocialType socialType;

    private String accessToken;
    private String refreshToken;


    public String getPayload(){
        return this.getId()+"+tripy";
    }

    public static Member toEntity(String email, String nickName, String profileImgUrl,
        SocialType socialType){
        return Member.builder()
            .nickName(nickName)
            .email(email)
            .profileImgUrl(profileImgUrl)
            .socialType(socialType)
            .build();
    }

    public void updateToken(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


}