package com.zenkodyazilim.langfella.features.article.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zenkodyazilim.langfella.common.models.BaseEntity;
import com.zenkodyazilim.langfella.features.article.entities.converters.LevelConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
public class Article extends BaseEntity {
    private String languageCode;

    private String title;
    private String source;

    @Convert(converter = LevelConverter.class)
    private Level level;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Chapter> chapters = new ArrayList<>();

    private int wordCount;
    private int uniqueWordCount;

    public static Article CreateByAuthors(
            String languageCode,
            String levelCode,
            String title
    ) {
        var article = new Article();
        article.setLanguageCode(languageCode);
        article.setLevel(Level.findByLevelCode(levelCode));
        article.setTitle(title);

        return article;
    }

    private static List<String> getAllWordsInAllChapters(List<Chapter> chapters) {
        return chapters
                .stream()
                .flatMap(c -> c.getContents().stream())
                .flatMap(ci -> Arrays.stream(ci.getContent().split(" ")))
                .toList();
    }

    public List<Chapter> getChapters() {
        return Collections.unmodifiableList(chapters);
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
        chapters.forEach(c -> c.setArticle(this));

        var allWords = getAllWordsInAllChapters(chapters);
        this.wordCount = allWords.size();
        this.uniqueWordCount = new HashSet<>(allWords).size();
    }

    public List<Author> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
        authors.forEach(a -> a.setArticle(this));
    }
}
