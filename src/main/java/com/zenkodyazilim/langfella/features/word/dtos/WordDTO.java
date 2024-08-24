package com.zenkodyazilim.langfella.features.word.dtos;

import com.zenkodyazilim.langfella.features.word.entities.Familiarity;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class WordDTO {
    private long id;
    private String text;
    private String sourceLanguageCode;
    private String targetLanguageCode;
    private Familiarity familiarity;
    private Long articleId;
    private Long chapterId;
    private Set<TranslationDTO> translations;
    private Set<ExampleSentenceDTO> exampleSentences;

    public static WordDTO of(Word word) {
        return WordDTO.builder()
                .id(word.getId())
                .articleId(word.getArticleId())
                .chapterId(word.getChapterId())
                .text(word.getText())
                .sourceLanguageCode(word.getSourceLanguageCode())
                .targetLanguageCode(word.getTargetLanguageCode())
                .familiarity(word.getFamiliarity())
                .translations(word.getTranslations().stream().map(TranslationDTO::of).collect(Collectors.toSet()))
                .exampleSentences(word.getExampleSentences().stream().map(ExampleSentenceDTO::of).collect(Collectors.toSet()))
                .build();
    }
}