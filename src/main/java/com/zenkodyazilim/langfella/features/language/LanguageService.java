package com.zenkodyazilim.langfella.features.language;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getLanguage() {
        return languageRepository.findAll();
    }

    public Optional<Language> isValidLanguage(String languageCode){
        return languageRepository.findLanguageByLanguageCode(languageCode);
    }
}
