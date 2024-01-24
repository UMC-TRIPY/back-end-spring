package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.bag.Bag;
import com.example.tripy.domain.cityplan.CityPlan;
import com.example.tripy.domain.planfriend.PlanFriend;
import com.example.tripy.domain.post.Post;
import com.example.tripy.domain.traveltimeplan.TravelTimePlan;
import com.example.tripy.domain.member.Member;
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

    private boolean bagExists; // 가방 존재 여부 확인

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public void updateBagExists(){
        //가방이 존재하지 않으면 true로 변경, 존재하면 false로 변경
        this.bagExists = !this.bagExists;
    }
}
