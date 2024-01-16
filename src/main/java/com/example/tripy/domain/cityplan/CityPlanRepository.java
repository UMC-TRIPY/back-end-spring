package com.example.tripy.domain.cityplan;

import com.example.tripy.domain.travelplan.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityPlanRepository extends JpaRepository<CityPlan, Long> {

    CityPlan findCityPlanByTravelPlan(TravelPlan travelPlan);
}
