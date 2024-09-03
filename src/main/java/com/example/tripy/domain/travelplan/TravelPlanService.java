package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.cityplan.CityPlanRepository;
import com.example.tripy.domain.cityplan.CityPlanService;
import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.travelplan.dto.TravelPlanRequestDto.CreateTravelPlanRequest;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanListSimpleInfo;
import com.example.tripy.domain.travelplan.dto.TravelPlanResponse.GetTravelPlanSimpleInfo;
import com.example.tripy.domain.traveltimeplan.TravelTimePlan;
import com.example.tripy.domain.traveltimeplan.TravelTimePlanRepository;
import com.example.tripy.domain.traveltimeplan.dto.TravelTimePlanRequestDto.CreateTravelTimePlanRequest;
import com.example.tripy.domain.traveltimeplan.dto.TravelTimePlanResponseDto.GetTravelTimePlanDetailInfo;
import com.example.tripy.domain.traveltimeplan.dto.TravelTimePlanResponseDto.GetTravelTimePlanSimpleInfo;
import com.example.tripy.global.common.PageResponseDto;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
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
    private final TravelTimePlanRepository travelTimePlanRepository;

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

    public List<GetTravelTimePlanSimpleInfo> getTravelPlanDetail(Member member, Long travelPlanId) {

        TravelPlan travelPlan = getTravelPlanById(member, travelPlanId);

        List<TravelTimePlan> travelTimePlans = travelTimePlanRepository.findTravelTimePlansByTravelPlan(
            travelPlan);

        return travelTimePlans.stream().
            map(GetTravelTimePlanSimpleInfo::toDTO)
            .collect(Collectors.toList());

    }

    private TravelPlan getTravelPlanById(Member member, Long travelPlanId) {
        return travelPlanRepository.findByMemberAndId(member, travelPlanId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_TRAVEL_PLAN));
    }


    @Transactional
    public GetTravelTimePlanSimpleInfo addTravelTimePlan(Member member, Long travelPlanId,
        CreateTravelTimePlanRequest createTravelTimePlanRequest) {

        TravelPlan travelPlan = getTravelPlanById(member, travelPlanId);

        TravelTimePlan travelTimePlan = TravelTimePlan.toEntity(createTravelTimePlanRequest,
            travelPlan);
        travelTimePlanRepository.save(travelTimePlan);
        return GetTravelTimePlanSimpleInfo.toDTO(travelTimePlan);
    }

    @Transactional
    public GetTravelTimePlanSimpleInfo updateTravelTimePlan(Member member, Long travelPlanId,
        Long travelTimePlanId,
        CreateTravelTimePlanRequest createTravelTimePlanRequest) {

        TravelPlan travelPlan = getTravelPlanById(member, travelPlanId);
        TravelTimePlan travelTimePlan = getTravelTimePlanById(travelPlan, travelTimePlanId);
        return GetTravelTimePlanSimpleInfo.toDTO(
            travelTimePlan.updateTravelTimePlan(createTravelTimePlanRequest));

    }

    private TravelTimePlan getTravelTimePlanById(TravelPlan travelPlan, Long travelTimePlanId) {
        return travelTimePlanRepository.findByTravelPlanAndId(travelPlan, travelTimePlanId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_TRAVEL_TIME_PLAN));
    }

    @Transactional
    public String deleteTravelTimePlan(Member member, Long travelPlanId, Long travelTimePlanId) {
        TravelPlan travelPlan = getTravelPlanById(member, travelPlanId);
        TravelTimePlan travelTimePlan = getTravelTimePlanById(travelPlan, travelTimePlanId);
        travelTimePlanRepository.delete(travelTimePlan);
        return "일정 삭제 완료";
    }

    public GetTravelTimePlanDetailInfo getTravelTimePlanDetail(Member member, Long travelPlanId,
        Long travelTimePlanId) {
        TravelPlan travelPlan = getTravelPlanById(member, travelPlanId);
        TravelTimePlan travelTimePlan = getTravelTimePlanById(travelPlan, travelTimePlanId);
        return GetTravelTimePlanDetailInfo.toDTO(travelTimePlan);
    }

}
