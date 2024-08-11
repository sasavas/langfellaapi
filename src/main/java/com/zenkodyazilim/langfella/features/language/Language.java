package com.zenkodyazilim.langfella.features.language;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Language{
    private String languageCode;
    private String fullName;

    public static final Language TURKISH = new Language("tr", "Türkçe");
    public static final Language ENGLISH = new Language("en", "English");

    public static final List<Language> LANGUAGE_LIST = List.of(TURKISH, ENGLISH);
}
