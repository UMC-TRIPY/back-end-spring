package com.example.tripy.domain.country;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

	Optional<Country> findCountryByName(String name);
}
