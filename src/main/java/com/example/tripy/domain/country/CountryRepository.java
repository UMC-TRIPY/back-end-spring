package com.example.tripy.domain.country;

import com.example.tripy.domain.language.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByLanguage(Language language);
}
