package com.zenkodyazilim.langfella.domainTests.article;

import com.zenkodyazilim.langfella.features.article.entities.Article;
import com.zenkodyazilim.langfella.features.article.entities.Chapter;
import com.zenkodyazilim.langfella.features.article.entities.ContentItem;
import com.zenkodyazilim.langfella.features.article.entities.ContentTag;
import com.zenkodyazilim.langfella.features.article.exceptions.IllegalLevelCodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ArticleTest {

    private Article article;

    @BeforeEach
    public void setUp() {
        ContentItem contentItem1 = ContentItem.Create(ContentTag.H1, "Chapter Title");
        ContentItem contentItem2 = ContentItem.Create(ContentTag.P, "This is a short short story about Japan.");
        Chapter chapter = Chapter.of(null, "Test Chapter Title", List.of(contentItem1, contentItem2));

        article = Article.CreateByAuthors("en", "A1", "Title");
        article.setChapters(List.of(chapter));
    }

    @Test
    public void testArticleCreation() {
        assertThat(article.getLanguageCode()).isEqualTo("en");
        assertThat(article.getTitle()).isEqualTo("Title");
        assertThat(article.getLevel().code()).isEqualTo("A1");
        assertThat(article.getChapters()).hasSize(1);
    }

    @Test
    public void testChapters(){
        assertThat(article.getChapters().size()).isEqualTo(1);
    }

    @Test
    public void testWordCounts() {
        assertThat(article.getWordCount()).isEqualTo(10);
        assertThat(article.getUniqueWordCount()).isEqualTo(9);
    }

    @Test
    public void testInvalidLevelCode() {
        assertThatThrownBy(() -> Article.CreateByAuthors("en", "InvalidCode", "Title"))
                .isInstanceOf(IllegalLevelCodeException.class)
                .hasMessageContaining("InvalidCode");
    }
}