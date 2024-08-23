package com.zenkodyazilim.langfella.features.translation.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationRequest {
    @NotNull
    @NotEmpty
    private String sourceLanguage;
    @NotNull
    @NotEmpty
    private String targetLanguage;
    @NotNull
    @NotEmpty
    private String word;
}
