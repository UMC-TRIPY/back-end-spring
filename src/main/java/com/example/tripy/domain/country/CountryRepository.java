package com.example.tripy.domain.country;

import com.example.tripy.domain.language.Language;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findCountryByName(String name);

    Country findByLanguage(Language language);
}