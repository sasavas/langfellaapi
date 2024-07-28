package com.zenkodyazilim.langfella.features.article.entities;

import com.zenkodyazilim.langfella.features.article.entities.converters.LevelConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<>();

    private int wordCount;
    private int uniqueWordCount;

    public static Article CreateByAuthors(
            String languageCode,
            String levelCode,
            String title,
            List<Author> authors
    ){
        return Article.builder()
                .languageCode(languageCode)
                .level(Level.findByLevelCode(levelCode))
                .title(title)
                .authors(authors)
                .build();
    }
//    public List<Word> words;
}
