package com.example.tripy.domain.travelplan;

import com.example.tripy.domain.member.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {

    Optional<TravelPlan> findByMemberAndIdAndBagExistsIsFalse(Member member, Long id);

    Page<TravelPlan> findAllByMemberIdAndBagExistsIsTrue(Long memberId, Pageable pageable);

    Optional<TravelPlan> findByMemberAndIdAndBagExistsIsTrue(Member member, Long id);


}
