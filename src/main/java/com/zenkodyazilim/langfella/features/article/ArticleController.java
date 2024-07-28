package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.features.article.dtos.ArticleDTO;
import com.zenkodyazilim.langfella.features.article.dtos.CreateArticleDTO;
import com.zenkodyazilim.langfella.features.article.entities.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody CreateArticleDTO createArticleDTO) {
        var created = articleService.createArticle(createArticleDTO);
        return ResponseEntity.ok(created);
    }

    // TODO
    //    @GetMapping("{articleId}/words")
    //    public List<WordDto> getArticleWords(long articleId){
    //        return ResponseEntity.ok(articleService.getArticleWords());
    //    }

    @GetMapping("{articleId}")
    public ResponseEntity<ArticleDTO> getById(Long articleId) {
        return ResponseEntity.ok(articleService.getArticleById(articleId));
    }

    @DeleteMapping("{articleId}")
    public ResponseEntity<Void> deleteArticle(Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("allLanguageLevels")
    public ResponseEntity<List<Level>> getAllLevels() {
        return ResponseEntity.ok(Level.AllLevels);
    }
}
