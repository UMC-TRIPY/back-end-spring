package com.example.tripy.domain.traveltimeplan;

import com.example.tripy.domain.travelplan.TravelPlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelTimePlanRepository extends JpaRepository<TravelTimePlan, Long> {

    List<TravelTimePlan> findTravelTimePlansByTravelPlan(TravelPlan travelPlan);

    Optional<TravelTimePlan> findByTravelPlanAndId(TravelPlan travelPlan, Long travelTimePlanId);
}
