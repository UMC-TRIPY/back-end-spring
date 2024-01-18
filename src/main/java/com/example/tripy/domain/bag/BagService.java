package com.example.tripy.domain.bag;

import com.example.tripy.domain.bag.dto.BagResponseDto.BagSimpleInfo;
import com.example.tripy.domain.cityplan.CityPlan;
import com.example.tripy.domain.cityplan.CityPlanRepository;
import com.example.tripy.global.common.dto.PageResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BagService {

    private final BagRepository bagRepository;
    private final CityPlanRepository cityPlanRepository;

    // Bag에 대한 간단한 일정 정보 Dto에 매핑
    public List<BagSimpleInfo> setBagSimpleInfo(List<Bag> bags) {
        List<BagSimpleInfo> bagSimpleInfos = new ArrayList<>();
        List<String> cities = new ArrayList<>();

        // 각각의 Bag 들에 대해 CityPlan을 받아와 연관 관계를 통해 city 이름을 저장
        for (Bag bag : bags) {
            //CityPlan을 모두 가져와 해당 CitPlan에 해당하는 City들을 List<String>에 저장
            List<CityPlan> cityPlans = cityPlanRepository.findAllByTravelPlan(bag.getTravelPlan());

            for(CityPlan cityPlan : cityPlans){
                cities.add(cityPlan.getCity().getName());
            }
            bagSimpleInfos.add(
                new BagSimpleInfo(bag.getTravelPlan().getDeparture(), bag.getTravelPlan()
                    .getArrival(), cities,bag.getTravelPlan().getId()));
        }

        return bagSimpleInfos;
    }

    // 내 여행 가방 모두 불러오기
    public PageResponseDto<List<BagSimpleInfo>> getBagsList(int page, int size, Long memberId) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Bag> result = bagRepository.findAllByMemberId(memberId, pageable);

        return new PageResponseDto<>(result.getNumber(), result.getTotalPages(),
            setBagSimpleInfo(result
                .stream()
                .collect(Collectors.toList())));
    }


}
