package com.zenkodyazilim.langfella.features.article.dtos;

import com.zenkodyazilim.langfella.features.article.entities.Article;
import com.zenkodyazilim.langfella.features.article.entities.Author;

import java.util.List;

public record ArticleDTO(
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
    public static ArticleDTO FromArticle(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getLanguageCode(),
                article.getLevel().code(),
                article.getTitle(),
                article.getSource(),
                article.getAuthors(),
                article.getChapters().size(),
                article.getWordCount(),
                article.getUniqueWordCount());
    }
}
