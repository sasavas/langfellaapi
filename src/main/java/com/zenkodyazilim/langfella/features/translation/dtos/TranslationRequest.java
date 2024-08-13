package com.zenkodyazilim.langfella.features.translation.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationRequest {
    @NotEmpty private String sourceLanguage;
    @NotEmpty private String targetLanguage;
    @NotEmpty private String word;
}
