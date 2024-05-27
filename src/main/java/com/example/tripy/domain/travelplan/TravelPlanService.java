package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.city.City;
import com.example.tripy.domain.cityplan.CityPlan;
import com.example.tripy.domain.cityplan.CityPlanService;
import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.member.MemberRepository;
import com.example.tripy.domain.travelplan.dto.TravelPlanRequestDto.CreateTravelPlanRequest;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TravelPlanService {
    private final TravelPlanRepository travelPlanRepository;
    private final CityPlanService cityPlanService;

    @Transactional
    public void addTravelPlan(Member member, CreateTravelPlanRequest createTravelPlanRequest){
        TravelPlan travelPlan = TravelPlan.toEntity(createTravelPlanRequest, member);
        travelPlanRepository.save(travelPlan);
        List<String> cityLists = createTravelPlanRequest.getCityNameList();
        cityPlanService.addCityPlan(cityLists, travelPlan);
    }
}
