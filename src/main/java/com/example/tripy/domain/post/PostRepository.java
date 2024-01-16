package com.example.tripy.domain.post;

import com.example.tripy.domain.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCountry(Long countryId);

}
