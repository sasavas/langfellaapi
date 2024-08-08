package com.zenkodyazilim.langfella.features.word.dtos;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;

import java.util.Optional;
import java.util.Set;

public record CreateWordDTO(
        @Nonnull String text,
        @Nonnull String sourceLanguageCode,
        @Nonnull String targetLanguageCode,
        @Nonnull @NotEmpty Set<String> translations,
        Optional<String> exampleSentence,
        Optional<Integer> familiarity,
        Optional<Long> articleId,
        Optional<Long> chapterId
) {
}
