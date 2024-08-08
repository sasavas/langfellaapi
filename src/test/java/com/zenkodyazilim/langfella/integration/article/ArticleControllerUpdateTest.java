package com.zenkodyazilim.langfella.integration.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenkodyazilim.langfella.features.article.ArticleService;
import com.zenkodyazilim.langfella.features.article.dtos.AddWordToArticleDTO;
import com.zenkodyazilim.langfella.features.article.dtos.ChapterCreateDTO;
import com.zenkodyazilim.langfella.features.article.dtos.CreateArticleDTO;
import com.zenkodyazilim.langfella.features.article.entities.Level;
import com.zenkodyazilim.langfella.features.word.entities.Familiarity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticleControllerUpdateTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArticleService articleService;

    @Test
    @DirtiesContext
    public void testCreateArticle() throws Exception {
        CreateArticleDTO createArticleDTO = new CreateArticleDTO(
                "en",
                "Test Title",
                Level.A1.code(),
                List.of(),
                List.of(new ChapterCreateDTO("Chapter 1", "Story begins here"))

        );

        String json = objectMapper.writeValueAsString(createArticleDTO);

        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.level").value("A1"));
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testAddWordToArticle() throws Exception {
        var article = articleService.createArticle(new CreateArticleDTO(
                "en",
                "Title",
                Level.B1.code(),
                List.of(),
                List.of(new ChapterCreateDTO("Chapter 1", "Chapter Content")))); // throws

        AddWordToArticleDTO addWordToArticleDTO = new AddWordToArticleDTO(
                article.id(),
                "fr",
                "word",
                Set.of("mot"),
                Optional.of("This is a word"),
                Optional.of(Familiarity.NEW.getLevel())
        );

        String json = objectMapper.writeValueAsString(addWordToArticleDTO);

        mockMvc.perform(post("/api/articles/" + article.id() + "/addWord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(jsonPath("$.articleId").value(1))
                .andExpect(jsonPath("$.chapterId").value(1));
    }

    @Test
    @DirtiesContext
    public void testDeleteArticle() throws Exception {
        // First, create an article
        CreateArticleDTO createArticleDTO = new CreateArticleDTO(
                "en",
                "Test Title",
                Level.A1.code(),
                new ArrayList<>(),
                List.of(new ChapterCreateDTO("Title", "Chapter Content"))
        );
        var articleDTO = articleService.createArticle(createArticleDTO);

        // Now, test deleting the article
        mockMvc.perform(delete("/api/articles/" + articleDTO.id()))
                .andExpect(status().isNoContent());

        // Verify that the article was deleted
        mockMvc.perform(get("/api/articles/" + articleDTO.id()))
                .andExpect(status().isNotFound());
    }
}
