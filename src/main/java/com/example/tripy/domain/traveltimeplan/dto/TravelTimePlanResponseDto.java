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
    public static class GetTravelTimePlanDetailInfo {

        Long travelTimePlanId;
        String color;
        String lineColor;
        String title;
        String memo;
        LocalTime departure;
        Date date;

        public static GetTravelTimePlanDetailInfo toDTO(TravelTimePlan travelTimePlan) {
            return GetTravelTimePlanDetailInfo.builder()
                .travelTimePlanId(travelTimePlan.getId())
                .color(travelTimePlan.getColor())
                .lineColor(travelTimePlan.getLineColor())
                .title(travelTimePlan.getTitle())
                .memo(travelTimePlan.getMemo())
                .departure(travelTimePlan.getDeparture())
                .date(travelTimePlan.getDate())
                .build();

        }
    }
}
