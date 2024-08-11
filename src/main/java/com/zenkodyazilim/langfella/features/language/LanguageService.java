package com.zenkodyazilim.langfella.features.language;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    public List<Language> getLanguages() {
        return Language.LANGUAGE_LIST;
    }

    public boolean isValidLanguage(String languageCode) {
        return Language.LANGUAGE_LIST.stream().anyMatch(l -> l.getLanguageCode().equalsIgnoreCase(languageCode));
    }
}
