package com.example.tripy.domain.comment;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.post.Post;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Comment extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Post post;

    public static Comment toEntity(Member member, String content) {
        return Comment.builder()
            .member(member)
            .content(content)
            .build();
    }
}