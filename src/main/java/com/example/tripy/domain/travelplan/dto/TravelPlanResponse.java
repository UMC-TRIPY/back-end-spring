package com.example.tripy.domain.travelplan.dto;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.travelplan.TravelPlan;
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

        long planId;

        public static GetTravelPlanSimpleInfo toDTO(Long planId) {
            return GetTravelPlanSimpleInfo.builder()
                .planId(planId)
                .build();

        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTravelPlanDetailInfo {

        private LocalDate startDate;
        private LocalDate endDate;
        private List<City> cityList;


    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetTravelPlanListSimpleInfo {

        Long travelPlanId;
        LocalDate departureDate;
        LocalDate arrivalDate;
        Boolean bagExists;
        List<String> cities;

        public static GetTravelPlanListSimpleInfo toDTO(TravelPlan travelPlan,
            List<String> cities) {
            return GetTravelPlanListSimpleInfo.builder()
                .travelPlanId(travelPlan.getId())
                .departureDate(travelPlan.getArrivalDate())
                .arrivalDate(travelPlan.getArrivalDate())
                .bagExists(travelPlan.getBagExists())
                .cities(cities)
                .build();
        }

    }


}
