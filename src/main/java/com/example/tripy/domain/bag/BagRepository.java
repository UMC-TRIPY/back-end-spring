package com.example.tripy.domain.bag;

import com.example.tripy.domain.travelplan.TravelPlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagRepository extends JpaRepository<Bag, Long> {

	Page<Bag> findAllByMemberId(Long memberId, Pageable pageable);

	List<Bag> findBagsByTravelPlan(TravelPlan travelPlan);

	Optional<Bag> findBagByIdAndTravelPlanId(Long id, Long travelPlanId);

}
