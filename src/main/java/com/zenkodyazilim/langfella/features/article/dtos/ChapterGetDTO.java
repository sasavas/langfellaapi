package com.zenkodyazilim.langfella.features.article.dtos;

import com.zenkodyazilim.langfella.features.article.entities.Chapter;

public record ChapterGetDTO(String title, String summary) {
    public static ChapterGetDTO of(Chapter chapter){
        return new ChapterGetDTO(chapter.getTitle(), chapter.getSummary());
    }
}
