package com.example.tripy.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostCreateRequestDto {
    //제목
    private String title;

    //내용
    private String content;

    //장소
    private String address;

    //특정 palnId를 선택한다고 가정
    private Long travelPlanId;

    //도시코드
    private Long countryId;
}
