package com.zenkodyazilim.langfella.features.article.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zenkodyazilim.langfella.features.article.exceptions.ChapterMustHaveContentException;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ContentItem> contents;

    private int wordCount;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonBackReference
    private Article article;

    public static Chapter Create(Article article, String title, List<ContentItem> contentItems) {
        return Chapter.builder()
                .title(title)
                .article(article)
                .contents(contentItems)
                .wordCount(contentItems.stream()
                        .map(c -> c.getContent().split(" ").length)
                        .reduce(Integer::sum)
                        .orElseThrow())
                .build();
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
