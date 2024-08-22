package com.zenkodyazilim.langfella.features.word.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;
import java.util.Set;

public record CreateWordDTO(
        @NotNull String text,
        @NotNull String sourceLanguageCode,
        @NotNull String targetLanguageCode,
        @NotNull @NotEmpty Set<String> translations,
        Optional<String> exampleSentence,
        Optional<Integer> familiarity,
        Optional<Long> articleId,
        Optional<Long> chapterId
) {
}
