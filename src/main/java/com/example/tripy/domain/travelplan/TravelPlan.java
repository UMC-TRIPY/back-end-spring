package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.bag.Bag;
import com.example.tripy.domain.cityplan.CityPlan;
import com.example.tripy.domain.planfriend.PlanFriend;
import com.example.tripy.domain.post.Post;
import com.example.tripy.domain.traveltimeplan.TravelTimePlan;
import com.example.tripy.domain.user.User;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TravelPlan extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departure;

    @Temporal(TemporalType.TIMESTAMP)
    private Date arrival;

    @OneToMany(mappedBy = "travelPlan", fetch = FetchType.LAZY)
    private List<CityPlan> cityPlans = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", fetch = FetchType.LAZY)
    private List<Bag> bags = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", fetch = FetchType.LAZY)
    private List<PlanFriend> planFriends = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "travelPlan", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", fetch = FetchType.LAZY)
    private List<TravelTimePlan> travelTimePlans = new ArrayList<>();

}
