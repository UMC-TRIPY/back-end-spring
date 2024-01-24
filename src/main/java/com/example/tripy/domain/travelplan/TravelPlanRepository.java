package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {

    Optional<TravelPlan> findByMemberAndId(Member member, Long id);
}
