package com.example.tripy.domain.city;

import io.swagger.v3.oas.annotations.Operation;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String cityName);
}
