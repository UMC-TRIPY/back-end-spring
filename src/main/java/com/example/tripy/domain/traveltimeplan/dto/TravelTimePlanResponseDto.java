package com.example.tripy.domain.traveltimeplan.dto;

import com.example.tripy.domain.traveltimeplan.TravelTimePlan;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TravelTimePlanResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTravelTimePlanDetailInfo {

        Long travelTimePlanId;
        String color;
        String lineColor;
        String title;
        LocalTime departure;
        int halfHourReputationCount;
        String place;

        String budget;

        String memo;

        String imageUrl;

        String planUrl;

        public static GetTravelTimePlanDetailInfo toDTO(TravelTimePlan travelTimePlan) {
            return GetTravelTimePlanDetailInfo.builder()
                .travelTimePlanId(travelTimePlan.getId())
                .color(travelTimePlan.getColor())
                .lineColor(travelTimePlan.getLineColor())
                .title(travelTimePlan.getTitle())
                .departure(travelTimePlan.getDeparture())
                .halfHourReputationCount(travelTimePlan.getHalfHourReputationCount())
                .place(travelTimePlan.getPlace())
                .budget(travelTimePlan.getBudget())
                .memo(travelTimePlan.getMemo())
                .imageUrl(travelTimePlan.getImageUrl())
                .planUrl(travelTimePlan.getPlanUrl())
                .build();

        }
    }
}
