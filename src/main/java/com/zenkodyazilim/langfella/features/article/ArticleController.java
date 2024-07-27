package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.features.article.dtos.ArticleListItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleListItemDto>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }
}
