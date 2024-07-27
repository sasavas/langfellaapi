package com.zenkodyazilim.langfella.features.article.entities;

import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "content_item")
public class ContentItem extends BaseEntity {
    private ContentTag tag;
    private String content;

    private Chapter chapter;
}
