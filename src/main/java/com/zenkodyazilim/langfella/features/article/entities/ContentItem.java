package com.zenkodyazilim.langfella.features.article.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "content_item")
public class ContentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ContentTag tag;
    private String content;

//    private Chapter chapter;

    public static ContentItem Create(ContentTag tag, String storyLine){
        return ContentItem.builder().tag(tag).content(storyLine).build();
    }
}
