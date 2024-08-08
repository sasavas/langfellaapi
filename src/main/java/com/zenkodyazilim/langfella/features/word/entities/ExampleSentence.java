package com.zenkodyazilim.langfella.features.word.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ExampleSentence extends BaseEntity {
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
