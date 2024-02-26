package com.example.tripy.domain.language;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByLanguageName(String languageName);
}
