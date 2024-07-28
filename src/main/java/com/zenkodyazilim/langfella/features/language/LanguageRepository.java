package com.zenkodyazilim.langfella.features.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {
    Optional<Language> findLanguageByLanguageCode(String languageCode);
    boolean existsByLanguageCode(String languageCode);
}
