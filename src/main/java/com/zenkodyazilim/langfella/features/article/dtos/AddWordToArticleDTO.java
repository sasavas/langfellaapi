package com.zenkodyazilim.langfella.features.article.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;
import java.util.Set;

public record AddWordToArticleDTO(
        @NotNull long chapterId,
        //TODO consider using the user's currently active Main Language instead of mixing target languages all over the place
        @NotNull String targetLanguageCode,
        @NotNull String text,
        @NotNull @NotEmpty Set<String> translations,
        Optional<String> exampleSentence,
        Optional<Integer> familiarity
) {
}
