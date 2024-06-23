package com.example.tripy.domain.travelplan.dto;

import com.example.tripy.domain.city.City;
import java.time.LocalDate;
import java.util.List;
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
        private long planId;

        public static GetTravelPlanSimpleInfo toDTO(Long planId){
            return GetTravelPlanSimpleInfo.builder()
                .planId(planId)
                .build();

        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTravelPlanDetailInfo{
        private LocalDate startDate;
        private LocalDate endDate;
        private List<City> cityList;


    }



}
