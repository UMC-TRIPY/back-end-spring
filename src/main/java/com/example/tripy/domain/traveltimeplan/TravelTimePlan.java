package com.example.tripy.domain.traveltimeplan;

import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.global.utils.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TravelTimePlan extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String color;

    @NotNull
    private String lineColor;

    @NotNull
    private String title;

    @NotNull
    private int column;

    @NotNull
    private LocalTime departure;

    @NotNull
    private int halfHourReputationCount;

    private String place;

    private String budget;

    private String memo;

    private String imageUrl;

    private String planUrl;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travelplan_id")
    private TravelPlan travelPlan;


}
