package com.zenkodyazilim.langfella.features.article.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zenkodyazilim.langfella.features.article.entities.converters.LevelConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "articles")
@Getter
@Setter
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
        return Article.builder()
                .languageCode(languageCode)
                .level(Level.findByLevelCode(levelCode))
                .title(title)
                .build();
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
        var allWords = getAllWordsInAllChapters(chapters);
        this.wordCount = allWords.size();
        this.uniqueWordCount = new HashSet<>(allWords).size();
    }

    private static List<String> getAllWordsInAllChapters(List<Chapter> chapters) {
        return chapters
                .stream()
                .flatMap(c -> c.getContents().stream())
                .flatMap(ci -> Arrays.stream(ci.getContent().split(" ")))
                .toList();
    }


//    public List<Word> words;
}
