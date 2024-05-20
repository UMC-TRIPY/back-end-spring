package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.member.Member;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Boolean bagExists = false; // 가방 존재 여부 확인

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public void updateBagExists() {
        //가방이 존재하지 않으면 true로 변경, 존재하면 false로 변경
        this.bagExists = !this.bagExists;
    }

}
