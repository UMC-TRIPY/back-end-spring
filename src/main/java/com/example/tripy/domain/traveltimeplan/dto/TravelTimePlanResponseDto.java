package com.example.tripy.domain.traveltimeplan.dto;

import com.example.tripy.domain.traveltimeplan.TravelTimePlan;
import java.time.LocalTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TravelTimePlanResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTravelTimePlanSimpleInfo {

        Long travelTimePlanId;
        String color;
        String lineColor;
        String title;
        String memo;
        String place;
        LocalTime departure;
        int halfHourReputationCount;
        Date date;

        public static GetTravelTimePlanSimpleInfo toDTO(TravelTimePlan travelTimePlan) {
            return GetTravelTimePlanSimpleInfo.builder()
                .travelTimePlanId(travelTimePlan.getId())
                .color(travelTimePlan.getColor())
                .lineColor(travelTimePlan.getLineColor())
                .title(travelTimePlan.getTitle())
                .memo(travelTimePlan.getMemo())
                .place(travelTimePlan.getPlace())
                .departure(travelTimePlan.getDeparture())
                .halfHourReputationCount(travelTimePlan.getHalfHourReputationCount())
                .date(travelTimePlan.getDate())
                .build();

        }
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTravelTimePlanDetailInfo {

        String color;
        String lineColor;
        String title;
        Date date;
        LocalTime departure;
        int halfHourReputationCount;
        String place;
        String budget;
        String memo;
        String imageUrl;
        String planUrl;

        public static GetTravelTimePlanDetailInfo toDTO(TravelTimePlan travelTimePlan) {
            return GetTravelTimePlanDetailInfo.builder()
                .color(travelTimePlan.getColor())
                .lineColor(travelTimePlan.getLineColor())
                .title(travelTimePlan.getTitle())
                .date(travelTimePlan.getDate())
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
