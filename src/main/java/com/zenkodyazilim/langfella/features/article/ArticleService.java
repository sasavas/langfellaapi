package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import com.zenkodyazilim.langfella.features.article.dtos.ArticleDTO;
import com.zenkodyazilim.langfella.features.article.dtos.CreateArticleDTO;
import com.zenkodyazilim.langfella.features.article.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleDTO> getArticles() {
        return articleRepository.findAll()
                .stream().map(ArticleDTO::FromArticle)
                .toList();
    }

    public ArticleDTO getArticleById(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDTO::FromArticle)
                .orElseThrow(() -> new EntityNotFoundException(Article.class.getSimpleName(), articleId.toString()));
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public ArticleDTO createArticle(CreateArticleDTO createArticleDTO) {
        var article = Article.CreateByAuthors(
                createArticleDTO.languageCode(),
                createArticleDTO.levelCode(),
                createArticleDTO.title(),
                createArticleDTO.authors().stream().map(a -> Author.Create(a.firstName(), a.lastName())).toList());

        article.setChapters(createArticleDTO.chapters().stream()
                .map(createChapterDTO -> Chapter.Create(
                        article,
                        createChapterDTO.title(),
                        List.of(ContentItem.Create(ContentTag.P, createChapterDTO.storyLine())))
                ).toList());

        var created = articleRepository.save(article);

        return ArticleDTO.FromArticle(created);
    }
}
