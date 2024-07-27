package com.zenkodyazilim.langfella.features.article.entities;


import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chapter")
public class Chapter extends BaseEntity {
    private String title;
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentItem> contents = new ArrayList<>();
    private int wordCount;
    private Article article;
}
