package com.zenkodyazilim.langfella.features.article.entities;

import com.zenkodyazilim.langfella.features.article.entities.converters.LevelConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
@Getter
@Setter
public class Article {
    @Id
    private Long id;

    private String languageCode;

    private String title;
    private String source;

    @Convert(converter = LevelConverter.class)
    private String level;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<>();

    private int wordCount;
    private int uniqueWordCount;
//    public List<Word> words;
}
