package com.zenkodyazilim.langfella.features.article.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "content_item")
public class ContentItem extends BaseEntity {
    private ContentTag tag;
    private String content;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    @JsonBackReference
    private Chapter chapter;

    public static ContentItem Create(ContentTag tag, String storyLine){
        return ContentItem.builder().tag(tag).content(storyLine).build();
    }
}
