package com.example.tripy.domain.cityplan;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.city.CityRepository;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CityPlanService {
    private final CityRepository cityRepository;
    private final CityPlanRepository cityPlanRepository;

    @Transactional
    public void addCityPlan(List<String> cityList, TravelPlan travelPlan){
        for(String cityName : cityList){
            City city = cityRepository.findByName(cityName)
                .orElseThrow(()->new GeneralException(ErrorStatus._EMPTY_CITY));
            CityPlan cityPlan = CityPlan.toEntity(travelPlan, city);
            cityPlanRepository.save(cityPlan);

        }
    }
}
