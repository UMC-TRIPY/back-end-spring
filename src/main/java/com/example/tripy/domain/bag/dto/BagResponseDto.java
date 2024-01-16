package com.example.tripy.domain.bag.dto;

import com.example.tripy.domain.travelplan.TravelPlan;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BagResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BagSimpleInfo {

        private Date departure;
        private Date arrival;
        private String name;

        @Builder
        public BagSimpleInfo(String name, TravelPlan travelPlan) {
            this.departure = travelPlan.getDeparture();
            this.arrival = travelPlan.getArrival();
            this.name = name;
        }

    }
}
