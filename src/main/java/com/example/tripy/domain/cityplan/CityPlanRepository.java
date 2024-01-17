package com.example.tripy.domain.cityplan;

import com.example.tripy.domain.travelplan.TravelPlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityPlanRepository extends JpaRepository<CityPlan, Long> {


    List<CityPlan> findAllByTravelPlan(TravelPlan travelPlan);
}
