package com.example.tripy.domain.traveltimeplan;

import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.domain.traveltimeplan.dto.TravelTimePlanRequestDto.CreateTravelTimePlanRequest;
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
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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

    //private int column;

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

    public static TravelTimePlan toEntity(CreateTravelTimePlanRequest createTravelTimePlanRequest,
        TravelPlan travelPlan) {
        return TravelTimePlan.builder()
            .color(createTravelTimePlanRequest.getColor())
            .lineColor(createTravelTimePlanRequest.getLineColor())
            .title(createTravelTimePlanRequest.getTitle())
            .date(createTravelTimePlanRequest.getDate())
            .departure(createTravelTimePlanRequest.getDeparture())
            .halfHourReputationCount(createTravelTimePlanRequest.getHalfHourReputationCount())
            .place(createTravelTimePlanRequest.getPlace())
            .budget(createTravelTimePlanRequest.getBudget())
            .memo(createTravelTimePlanRequest.getMemo())
            .imageUrl(createTravelTimePlanRequest.getImageUrl())
            .planUrl(createTravelTimePlanRequest.getPlanUrl())
            .travelPlan(travelPlan)
            .build();
    }

    public TravelTimePlan updateTravelTimePlan(
        CreateTravelTimePlanRequest createTravelTimePlanRequest) {
        this.color = createTravelTimePlanRequest.getColor();
        this.lineColor = createTravelTimePlanRequest.getLineColor();
        this.title = createTravelTimePlanRequest.getTitle();
        this.date = createTravelTimePlanRequest.getDate();
        this.departure = createTravelTimePlanRequest.getDeparture();
        this.halfHourReputationCount = createTravelTimePlanRequest.getHalfHourReputationCount();
        this.place = createTravelTimePlanRequest.getPlace();
        this.budget = createTravelTimePlanRequest.getBudget();
        this.memo = createTravelTimePlanRequest.getMemo();
        this.imageUrl = createTravelTimePlanRequest.getImageUrl();
        this.planUrl = createTravelTimePlanRequest.getPlanUrl();
        return this;
    }

}
