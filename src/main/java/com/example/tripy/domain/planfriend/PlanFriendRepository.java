package com.example.tripy.domain.planfriend;

import com.example.tripy.domain.travelplan.TravelPlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanFriendRepository extends JpaRepository<PlanFriend, Long> {

    List<PlanFriend> findPlanFriendsByTravelPlan(TravelPlan travelPlan);


}
