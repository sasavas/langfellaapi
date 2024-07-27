package com.zenkodyazilim.langfella.features.article.dtos;

import com.zenkodyazilim.langfella.features.article.entities.Article;
import com.zenkodyazilim.langfella.features.article.entities.Author;

import java.util.ArrayList;
import java.util.List;

public record ArticleListItemDto(
        Long id,
        String languageCode,
        String level,
        String title,
        String source,
        List<Author> authors,
        int chapterCount,
        int wordCount,
        int uniqueWordCount
) {
    public static ArticleListItemDto FromArticle(Article article) {
        return new ArticleListItemDto(
                article.getId(),
                article.getLanguageCode(),
                "A1",
//                article.getLevel().code(),
                article.getTitle(),
                article.getSource(),
                new ArrayList<>(),
//                article.getAuthors(),
                10,
//                article.getChapters().size(),
                article.getWordCount(),
                article.getUniqueWordCount());
    }
}
