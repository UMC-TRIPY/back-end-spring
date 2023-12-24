package com.example.tripy.domain.post;

import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    // TODO: 2023/12/18 user,city, plan 연관관계 매핑

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private Long cityIndex;

    @ColumnDefault("0")
    private Long view;

    @ColumnDefault("0")
    private Integer thumbs;

    private Integer category;

    private Long planId;

}