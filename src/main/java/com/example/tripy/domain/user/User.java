package com.example.tripy.domain.user;

import com.example.tripy.domain.bag.Bag;
import com.example.tripy.domain.friend.Friend;
import com.example.tripy.domain.planfriend.PlanFriend;
import com.example.tripy.domain.post.Post;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class User extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nickName;

    @NotNull
    private String email;

    private String profileImgUrl;

    private Long kakaoToken;

    private Long googleToken;

    private Long appleToken;

    private Long naverToken;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Bag> bags = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser")
    private List<Friend> sentRequests = new ArrayList<>();

    @OneToMany(mappedBy = "toUser")
    private List<Friend> receivedRequests = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PlanFriend> planFriends = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TravelPlan> travelPlans = new ArrayList<>();



}