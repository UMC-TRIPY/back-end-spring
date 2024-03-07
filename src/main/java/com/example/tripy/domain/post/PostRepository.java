package com.example.tripy.domain.post;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.rank > 0 ORDER BY p.rank ASC limit 10")
    List<Post> findByRankToptenOrderByRank(Pageable pageable);
}