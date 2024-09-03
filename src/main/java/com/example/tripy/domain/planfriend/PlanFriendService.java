package com.example.tripy.domain.planfriend;

import com.example.tripy.domain.member.Member;
import com.example.tripy.domain.planfriend.dto.PlanFriendResponseDto.GetPlanFriendInfo;
import com.example.tripy.domain.travelplan.TravelPlan;
import com.example.tripy.domain.travelplan.TravelPlanRepository;
import com.example.tripy.global.response.code.status.ErrorStatus;
import com.example.tripy.global.response.exception.GeneralException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlanFriendService {

    private final PlanFriendRepository planFriendRepository;
    private final TravelPlanRepository travelPlanRepository;

    public List<GetPlanFriendInfo> getPlanFriends(Member member, Long travelPlanId) {

        TravelPlan travelPlan = travelPlanRepository.findByMemberAndId(member, travelPlanId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._EMPTY_TRAVEL_PLAN));

        List<PlanFriend> planFriends = planFriendRepository.findPlanFriendsByTravelPlan(travelPlan);

        return planFriends.stream()
            .map(GetPlanFriendInfo::toDTO)
            .toList();
    }

}
