package com.zenkodyazilim.langfella.features.article.entities;


import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chapter")
public class Chapter {
    @Id
    private Long id;

    private String title;

//    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ContentItem> contents = new ArrayList<>();

    private int wordCount;

    @ManyToOne(targetEntity = Article.class)
    private Article article;
}
