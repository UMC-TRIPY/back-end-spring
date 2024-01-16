package com.example.tripy.domain.post.dto;

import com.example.tripy.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long view;
    private Integer thumbs;

    public static PostResponseDto toDTO(Post entity){
        return PostResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .view(entity.getView())
                .thumbs(entity.getThumbs())
                .build();
    }
}
