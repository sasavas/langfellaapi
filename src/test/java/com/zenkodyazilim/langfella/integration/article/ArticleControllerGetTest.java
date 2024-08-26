package com.zenkodyazilim.langfella.integration.article;

import com.zenkodyazilim.langfella.features.article.ArticleService;
import com.zenkodyazilim.langfella.features.article.dtos.AuthorDTO;
import com.zenkodyazilim.langfella.features.article.dtos.ChapterCreateDTO;
import com.zenkodyazilim.langfella.features.article.dtos.CreateArticleDTO;
import com.zenkodyazilim.langfella.features.article.entities.Level;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@NoArgsConstructor
@DirtiesContext
public class ArticleControllerGetTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleService articleService;

    @BeforeEach
    public void setUp() {
        CreateArticleDTO createArticleDTO = new CreateArticleDTO(
                "en",
                Optional.of("Test Title"),
                Optional.of(Level.A2.code()),
                Optional.of(List.of(new AuthorDTO("Alp", "Savas"))),
                List.of(new ChapterCreateDTO(
                        Optional.of("Chapter 1"),
                        "This is the story of chapter 1"
                ))
        );
        articleService.createArticle(createArticleDTO);
    }

    @Test
    @DirtiesContext
    public void testGetArticles() throws Exception {
        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @DirtiesContext
    public void testGetById() throws Exception {
        // Now, test getting the article by ID
        var result = mockMvc.perform(get("/api/articles/" + 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.chapterSummaries.length()").value(1))
                .andExpect(jsonPath("$.authors.length()").value(1))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(result);
    }

    @Test
    public void testGetAllLevels() throws Exception {
        mockMvc.perform(get("/api/articles/languageLevels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(Level.AllLevels.size()));
    }
}
