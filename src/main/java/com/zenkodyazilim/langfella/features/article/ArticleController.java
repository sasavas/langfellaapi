package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.features.article.dtos.AddWordToArticleDTO;
import com.zenkodyazilim.langfella.features.article.dtos.ArticleDTO;
import com.zenkodyazilim.langfella.features.article.dtos.CreateArticleDTO;
import com.zenkodyazilim.langfella.features.article.entities.Level;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
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

    @GetMapping("{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable long id) {
        var article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("languageLevels")
    public ResponseEntity<List<Level>> getAllLevels() {
        return ResponseEntity.ok(Level.AllLevels);
    }

    @GetMapping("{id}/words")
    public ResponseEntity<List<Word>> getArticleWords(@PathVariable long id) {
        return ResponseEntity.ok(articleService.getArticleWords(id));
    }

    @PostMapping("{id}/words")
    public ResponseEntity<Word> addWordToArticle(
            @PathVariable long id, @RequestBody AddWordToArticleDTO addWordToArticleDTO) {
        var addedWord = articleService.addWordToArticle(id, addWordToArticleDTO);
        return ResponseEntity.ok(addedWord);
    }
}
