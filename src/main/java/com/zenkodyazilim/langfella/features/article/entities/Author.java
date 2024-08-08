package com.zenkodyazilim.langfella.features.article.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {
    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonBackReference
    private Article article;

    public static Author Create(String firstName, String lastName){
        return Author.builder().firstName(firstName).lastName(lastName).build();
    }
}
