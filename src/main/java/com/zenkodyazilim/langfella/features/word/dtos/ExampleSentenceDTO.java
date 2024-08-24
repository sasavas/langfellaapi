package com.zenkodyazilim.langfella.features.word.dtos;

import com.zenkodyazilim.langfella.features.word.entities.ExampleSentence;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExampleSentenceDTO {
    private String text;
    private Long articleId;
    private Long chapterId;

    public static ExampleSentenceDTO of(ExampleSentence es) {
        return ExampleSentenceDTO.builder()
                .text(es.getText())
                .articleId(es.getArticleId())
                .chapterId(es.getChapterId())
                .build();
    }
}
