package com.example.tripy.domain.travelplan.dto;


import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class TravelPlanRequestDto {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreateTravelPlanRequest{
        private List<String> cityNameList;
        private LocalDate departureDate;
        private LocalDate arrivalDate;


    }

}
