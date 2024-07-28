package com.zenkodyazilim.langfella.features.word.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExampleSentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Long chapterId;
    private Long articleId;

    @ManyToOne
    @JoinColumn(name = "word_id")
    @JsonBackReference
    private Word word;

    public ExampleSentence(String text){
        this.text = text;
    }
}
