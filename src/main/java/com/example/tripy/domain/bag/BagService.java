package com.example.tripy.domain.bag;

import com.example.tripy.domain.bag.dto.BagResponseDto.BagListSimpleInfo;
import com.example.tripy.domain.cityplan.CityPlanRepository;
import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.member.MemberRepository;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.domain.travelplan.TravelPlanRepository;
import com.example.tripy.global.common.dto.PageResponseDto;
import com.example.tripy.global.common.response.code.status.ErrorStatus;
import com.example.tripy.global.common.response.exception.GeneralException;
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

    private final CityPlanRepository cityPlanRepository;
    private final TravelPlanRepository travelPlanRepository;
    private final MemberRepository memberRepository;

    // Bag에 대한 간단한 일정 정보 Dto에 매핑
    public List<BagListSimpleInfo> setBagListSimpleInfo(List<TravelPlan> travelPlans) {
        return travelPlans.stream()
            .map(travelPlan -> {
                List<String> cities = cityPlanRepository.findAllByTravelPlan(travelPlan)
                    .stream()
                    .map(cityPlan -> cityPlan.getCity().getName())
                    .collect(Collectors.toList());

                return new BagListSimpleInfo(
                    travelPlan.getDeparture(),
                    travelPlan.getArrival(),
                    cities,
                    travelPlan.getId()
                );
            })
            .collect(Collectors.toList());
    }


    // 내 일정에 맞는 가방 목록 모두 불러오기
    public PageResponseDto<List<BagListSimpleInfo>> getTravelBagExistsList(int page, int size,
        Long memberId) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TravelPlan> result = travelPlanRepository.findAllByMemberIdAndBagExistsIsTrue(memberId,
            pageable);

        return new PageResponseDto<>(result.getNumber(), result.getTotalPages(),
            setBagListSimpleInfo(result
                .stream()
                .collect(Collectors.toList())));
    }

    @Transactional
    public String updateBagExists(Long memberId, Long travelPlanId) {

        //Member 관련 메서드가 추가되면 수정 예정
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_MEMBER));

        //bagExists 값이 False인 TravelPlan만 가능, 이미 가방이 존재하면 예외 처리
        TravelPlan travelPlan = travelPlanRepository.findByMemberAndIdAndBagExistsIsFalse(member,
                travelPlanId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._ALREADY_TRAVEL_PLAN_BAG_EXISTS));
        // 해당 여행 일정은 가방 목록이 생성되도록 상태 변경
        travelPlan.updateBagExists();

        return "내 가방 목록에 추가 완료";
    }

}
