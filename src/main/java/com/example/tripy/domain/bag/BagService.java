package com.example.tripy.domain.bag;

import com.example.tripy.domain.bag.dto.BagResponseDto.BagSimpleInfo;
import com.example.tripy.domain.cityplan.CityPlanRepository;
import com.example.tripy.global.common.dto.PageResponseDto;
import jakarta.transaction.Transactional;
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
        return bags.stream()
            .map(bag -> {
                List<String> cities = cityPlanRepository.findAllByTravelPlan(bag.getTravelPlan())
                    .stream()
                    .map(cityPlan -> cityPlan.getCity().getName())
                    .collect(Collectors.toList());

                return new BagSimpleInfo(
                    bag.getTravelPlan().getDeparture(),
                    bag.getTravelPlan().getArrival(),
                    cities,
                    bag.getTravelPlan().getId()
                );
            })
            .collect(Collectors.toList());
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

    @Transactional
    public String createBag(Long memberId, Long travelPlanId) {

        return "가방 생성 완료";
    }

}
