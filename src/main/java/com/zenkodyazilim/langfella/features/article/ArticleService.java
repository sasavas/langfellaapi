package com.zenkodyazilim.langfella.features.article;

import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import com.zenkodyazilim.langfella.features.article.dtos.AddWordToArticleDTO;
import com.zenkodyazilim.langfella.features.article.dtos.ArticleDTO;
import com.zenkodyazilim.langfella.features.article.dtos.CreateArticleDTO;
import com.zenkodyazilim.langfella.features.article.entities.*;
import com.zenkodyazilim.langfella.features.article.exceptions.ArticleMustContainAtLeastOneChapterException;
import com.zenkodyazilim.langfella.features.word.WordService;
import com.zenkodyazilim.langfella.features.word.dtos.CreateWordDTO;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final WordService wordService;

    public ArticleService(ArticleRepository articleRepository, WordService wordService) {
        this.articleRepository = articleRepository;
        this.wordService = wordService;
    }

    public List<ArticleDTO> getArticles() {
        return articleRepository.findAll()
                .stream().map(ArticleDTO::of)
                .toList();
    }

    public ArticleDTO getArticleById(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleDTO::of)
                .orElseThrow(() -> new EntityNotFoundException(Article.class.getSimpleName(), articleId.toString()));
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    @Transactional
    public ArticleDTO createArticle(CreateArticleDTO createArticleDTO) {
        // create article
        var article = Article.CreateByAuthors(
                createArticleDTO.languageCode(),
                createArticleDTO.levelCode().orElse(Level.Unabdridged.code()),
                createArticleDTO.title().orElse("No Title")
        );

        if (createArticleDTO.chapters() == null || createArticleDTO.chapters().isEmpty()) {
            throw new ArticleMustContainAtLeastOneChapterException();
        }

        // create and link chapters
        AtomicInteger chapterCount = new AtomicInteger(1);
        article.setChapters(createArticleDTO.chapters().stream()
                .map(createChapterDTO -> Chapter.of(
//                        article,
                        createChapterDTO.title().orElse("Chapter " + chapterCount.getAndIncrement()),
                        List.of(ContentItem.Create(ContentTag.P, createChapterDTO.storyLine())))
                )
                .toList());

        // create and link authors
        if (createArticleDTO.authors().isPresent()) {
            article.setAuthors(createArticleDTO.authors().get().stream()
                    .map(a -> Author.Create(a.firstName(), a.lastName())).toList());
        }


        var created = articleRepository.save(article);
        return ArticleDTO.of(created);
    }

    @Transactional
    public Word addWordToArticle(long articleId, AddWordToArticleDTO addWordToArticleDTO) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException(Article.class.getSimpleName(), String.valueOf(articleId)));

        return wordService.createWord(new CreateWordDTO(
                addWordToArticleDTO.text(),
                article.getLanguageCode(),
                addWordToArticleDTO.targetLanguageCode(),
                addWordToArticleDTO.translations(),
                addWordToArticleDTO.exampleSentence(),
                addWordToArticleDTO.familiarity(),
                Optional.of(articleId),
                Optional.of(addWordToArticleDTO.chapterId())
        ));
    }

    public List<Word> getArticleWords(long articleId) {
        return wordService.getWordsByArticleId(articleId);
    }
}
