package com.zenkodyazilim.langfella.features.article.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "content_item")
public class ContentItem {
    @Id
    private Long id;

    private ContentTag tag;
    private String content;

    @ManyToOne
    private Chapter chapter;
}
