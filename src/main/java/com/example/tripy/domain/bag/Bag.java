package com.example.tripy.domain.bag;

import com.example.tripy.domain.member.Member;
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

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bag extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String bagName;

    @NotNull
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travelplan_id")
    private TravelPlan travelPlan;

    @Builder
    public Bag(String bagName, String content, Member member, TravelPlan travelPlan) {
        this.bagName = bagName;
        this.content = content;
        this.member = member;
        this.travelPlan = travelPlan;
    }

}