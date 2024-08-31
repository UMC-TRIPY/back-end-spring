package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.cityplan.CityPlanRepository;
import com.example.tripy.domain.cityplan.CityPlanService;
import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.travelplan.dto.TravelPlanRequestDto.CreateTravelPlanRequest;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanListSimpleInfo;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanSimpleInfo;
import com.example.tripy.global.common.PageResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TravelPlanService {

    private final TravelPlanRepository travelPlanRepository;
    private final CityPlanService cityPlanService;
    private final CityPlanRepository cityPlanRepository;

    @Transactional
    public GetTravelPlanSimpleInfo addTravelPlan(Member member,
        CreateTravelPlanRequest createTravelPlanRequest) {
        TravelPlan travelPlan = TravelPlan.toEntity(createTravelPlanRequest, member);
        travelPlanRepository.save(travelPlan);
        List<String> cityLists = createTravelPlanRequest.getCityNameList();
        cityPlanService.addCityPlan(cityLists, travelPlan);
        return GetTravelPlanSimpleInfo.toDTO(travelPlan.getId());
    }

    public PageResponseDto<List<GetTravelPlanListSimpleInfo>> getTravelPlanExistsList(int page,
        int size, Member member) {
        Pageable pageable = PageRequest.of(page, size);

        Page<TravelPlan> result = travelPlanRepository.findAllByMemberAndBagExistsIsFalse(member,
            pageable);

        return new PageResponseDto<>(result.getNumber(), result.getTotalPages(),
            setTravelPlanListSimpleInfo(result.stream().collect(
                Collectors.toList())));
    }

    public List<GetTravelPlanListSimpleInfo> setTravelPlanListSimpleInfo(
        List<TravelPlan> travelPlans) {
        return travelPlans.stream()
            .map(travelPlan -> {
                List<String> cities = cityPlanRepository.findAllByTravelPlan(travelPlan)
                    .stream()
                    .map(cityPlan -> cityPlan.getCity().getName())
                    .collect(Collectors.toList());

                return GetTravelPlanListSimpleInfo.toDTO(travelPlan, cities
                );
            })
            .collect(Collectors.toList());

    }

}
