package com.zenkodyazilim.langfella.features.article.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zenkodyazilim.langfella.common.models.BaseEntity;
import com.zenkodyazilim.langfella.features.article.exceptions.ChapterMustHaveContentException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "chapter")
public class Chapter extends BaseEntity {
    private String title;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ContentItem> contents;

    private int wordCount;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    @JsonBackReference
    private Article article;

    public static Chapter of(Article article, String title, List<ContentItem> contentItems) {
        if (contentItems == null || contentItems.isEmpty()) {
            throw new ChapterMustHaveContentException();
        }

        var chapter = new Chapter();
        chapter.setTitle(title);
        chapter.setArticle(article);
        chapter.setContents(contentItems);
        chapter.setWordCount(contentItems.stream()
                .map(c -> c.getContent().split(" ").length)
                .reduce(Integer::sum)
                .orElseThrow(ChapterMustHaveContentException::new));

        contentItems.forEach(c -> c.setChapter(chapter));

        return chapter;
    }

    public static Chapter of(String title, List<ContentItem> contentItems) {
        if (contentItems == null || contentItems.isEmpty()) {
            throw new ChapterMustHaveContentException();
        }

        var chapter = new Chapter();

        chapter.setTitle(title);
        chapter.setContents(contentItems);
        chapter.setWordCount(contentItems.stream()
                .map(c -> c.getContent().split(" ").length)
                .reduce(Integer::sum)
                .orElseThrow(ChapterMustHaveContentException::new));

        return chapter;
    }

    public String getSummary() {
        return contents.stream()
                .filter(c -> c.getTag().equals(ContentTag.P))
                .map(ContentItem::getContent)
                .findFirst()
                .map(c -> c.length() >= 120 ? c.substring(0, 120) : c)
                .orElse("");
    }
}
