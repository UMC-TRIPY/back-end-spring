package com.example.tripy.domain.post;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.post.dto.PostCreateRequestDto;
import com.example.tripy.domain.postfile.PostFile;
import com.example.tripy.domain.posttag.PostTag;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.domain.user.User;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

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

    private String address;

    @ColumnDefault("0")
    private Long view;

    @ColumnDefault("0")
    private Integer thumbs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostFile> postFiles = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostTag> postTags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travelplan_id")
    private TravelPlan travelPlan;

    public void viewCountUp() {
        this.view++;
    }

    public void thumbsCountUp() {
        this.thumbs++;
    }

    public void thumbsCountDown() {
        this.thumbs--;
    }

    // TODO: 2024/01/05 PostFile, PostTag리스트 추가
    public static Post toEntity(PostCreateRequestDto requestDto, TravelPlan travelPlan){
        return Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .address(requestDto.getAddress())
                .travelPlan(travelPlan)
                .build();
    }

}