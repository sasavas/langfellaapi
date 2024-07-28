package com.zenkodyazilim.langfella.features.article.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToOne
    private Article article;

    public static Author Create(String firstName, String lastName){
        return Author.builder().firstName(firstName).lastName(lastName).build();
    }
}
