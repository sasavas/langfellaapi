package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.features.article.dtos.ArticleListItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleListItemDto> getArticles() {
        return articleRepository.findAll()
                .stream().map(ArticleListItemDto::FromArticle)
                .collect(Collectors.toList());
    }
}
