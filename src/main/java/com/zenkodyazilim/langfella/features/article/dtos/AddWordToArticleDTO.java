package com.zenkodyazilim.langfella.features.article.dtos;

import java.util.Optional;
import java.util.Set;

public record AddWordToArticleDTO(
        long chapterId,
        String targetLanguageCode,
        String text,
        Set<String> translations,
        Optional<String> exampleSentence,
        Optional<Integer> familiarity
) {
}
