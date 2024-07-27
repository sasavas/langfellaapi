package com.zenkodyazilim.langfella.features.article.entities;

import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author extends BaseEntity {
    private String firstName;
    private String lastName;

    private Article article;
}
