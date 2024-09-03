package com.example.tripy.domain.traveltimeplan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TravelTimePlanRequestDto {


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreateTravelTimePlanRequest {

        @Schema(description = "색상", example = "red")
        private String color;
        @Schema(description = "라인 색상", example = "yellow")
        private String lineColor;
        @Schema(description = "제목", example = "제목")
        private String title;
        @Schema(description = "날짜", example = "2024-09-01")
        private Date date;
        @Schema(description = "출발 시간", example = "08:30:00")
        private LocalTime departure;
        @Schema(description = "30분 반복 수", example = "1")
        private int halfHourReputationCount;
        @Schema(description = "장소", example = "장소")
        private String place;
        @Schema(description = "예산", example = "100000")
        private String budget;
        @Schema(description = "메모", example = "메모")
        private String memo;
        @Schema(description = "이미지 URL", example = "https://tripy-bucket.s3.ap-northeast-2.amazonaws.com/example.png")
        private String imageUrl;
        private String planUrl;
    }

}
