package com.zenkodyazilim.langfella.domainTests.article;

import com.zenkodyazilim.langfella.features.article.entities.Chapter;
import com.zenkodyazilim.langfella.features.article.entities.ContentItem;
import com.zenkodyazilim.langfella.features.article.entities.ContentTag;
import com.zenkodyazilim.langfella.features.article.exceptions.ChapterMustHaveContentException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ChapterTest {
    @Test
    public void testChapterMustHaveAtLeastOneContentItem() {
        assertThatThrownBy(() -> Chapter.Create(null, "Chapter 1", List.of()))
                .isInstanceOf(ChapterMustHaveContentException.class)
                .hasMessageContaining("Chapter must have at least one Content Item");
    }

    @Test
    public void testGetSummaryWithParagraphContent() {
        ContentItem contentItem1 = ContentItem.Create(ContentTag.P, "This is a short story about Japan.");
        ContentItem contentItem2 = ContentItem.Create(ContentTag.H1, "Chapter Title");

        Chapter chapter = Chapter.Create(null, "Test Chapter Title", List.of(contentItem1, contentItem2));
        contentItem1.setChapter(chapter);
        contentItem2.setChapter(chapter);

        assertThat(chapter.getSummary()).isEqualTo("This is a short story about Japan.");
    }

    @Test
    public void testGetSummaryWithLongParagraphContent() {
        String longContent = "This is a long story about Japan. ".repeat(10);
        ContentItem contentItem1 = ContentItem.Create(ContentTag.P, longContent);
        ContentItem contentItem2 = ContentItem.Create(ContentTag.H1, "Chapter Title");

        Chapter chapter = Chapter.Create(null, "Test Chapter Title", List.of(contentItem1, contentItem2));
        contentItem1.setChapter(chapter);
        contentItem2.setChapter(chapter);

        assertThat(chapter.getSummary()).isEqualTo(longContent.substring(0, 120));
    }

    @Test
    public void testGetSummaryWithNoParagraphContent() {
        ContentItem contentItem1 = ContentItem.Create(ContentTag.H1, "Chapter Title");

        Chapter chapter = Chapter.Create(null, "Test Chapter Title", List.of(contentItem1));
        contentItem1.setChapter(chapter);

        assertThat(chapter.getSummary()).isEqualTo("");
    }

    @Test
    public void testGetSummaryWithMultipleParagraphs() {
        ContentItem contentItem1 = ContentItem.Create(ContentTag.P, "This is the first paragraph.");
        ContentItem contentItem2 = ContentItem.Create(ContentTag.P, "This is the second paragraph.");
        ContentItem contentItem3 = ContentItem.Create(ContentTag.H1, "Chapter Title");

        Chapter chapter = Chapter.Create(null, "Test Chapter Title", List.of(contentItem1, contentItem2, contentItem3));
        contentItem1.setChapter(chapter);
        contentItem2.setChapter(chapter);
        contentItem3.setChapter(chapter);

        assertThat(chapter.getSummary()).isEqualTo("This is the first paragraph.");
    }
}