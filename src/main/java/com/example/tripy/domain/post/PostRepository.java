package com.example.tripy.domain.post;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.rank > 0 ORDER BY p.rank ASC")
    List<Post> findByRankTopOrderByRankNullCountryId(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.country.id = :countryId AND p.rank > 0 ORDER BY p.rank ASC")
    List<Post> findByRankTopOrderByRankNotNullCountryId(@Param("countryId") Long countryId, Pageable pageable);

    @Query("SELECT p FROM Post p ORDER BY p.recommendationCount DESC")
    Page<Post> findByTopRecommended(Pageable pageable);

    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}