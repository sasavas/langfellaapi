package com.zenkodyazilim.langfella.features.article.dtos;

import java.util.List;

public record CreateArticleDTO(
        String languageCode,
        String title,
        String levelCode,
        List<AuthorDTO> authors,
        List<ChapterCreateDTO> chapters) {
}