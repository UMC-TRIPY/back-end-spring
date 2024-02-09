package com.example.tripy.domain.wheather;

import com.example.tripy.domain.wheather.dto.WheatherResponseDto;
import com.example.tripy.domain.wheather.dto.WheatherResponseDto.WhetherResponseSimpleInfo;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    @GetMapping("/{cityId}")
    public ResponseEntity<List<WhetherResponseSimpleInfo>> getWeatherList(@PathVariable Long cityId){
        return ResponseEntity.ok(weatherService.getWeatherList(cityId));
    }
}
