package com.example.tripy.domain.post;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.country.Country;
import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.post.dto.PostRequestDto.CreatePostRequest;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @ColumnDefault("0")
    private Long view;

    @ColumnDefault("0")
    private Integer recommendationCount;

    @ColumnDefault("0")
    private int rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travelplan_id")
    private TravelPlan travelPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public static Post toEntity(CreatePostRequest createPostRequest, Member member, City city
        , TravelPlan travelPlan) {
        return Post.builder()
            .title(createPostRequest.getTitle())
            .content(createPostRequest.getContent())
            .member(member)
            .city(city)
            .travelPlan(travelPlan)
            .build();
    }
}