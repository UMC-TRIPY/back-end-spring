package com.example.tripy.domain.travelplan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TravelPlanResponse {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTravelPlanSimpleInfo {
        long planId;

        public static GetTravelPlanSimpleInfo toDto(Long planId){
            return GetTravelPlanSimpleInfo.builder()
                .planId(planId)
                .build();

        }
    }

}
