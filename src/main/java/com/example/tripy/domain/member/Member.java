package com.example.tripy.domain.member;

import com.example.tripy.domain.bag.Bag;
import com.example.tripy.domain.friend.Friend;
import com.example.tripy.domain.member.enums.SocialType;
import com.example.tripy.domain.planfriend.PlanFriend;
import com.example.tripy.domain.post.Post;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nickName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String profileImgUrl;

    private SocialType socialType;



}