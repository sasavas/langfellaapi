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
public class Translation extends BaseEntity {
    private String text;

    public Translation(String text){
        this.setText(text);
    }

    @ManyToOne
    @JoinColumn(name = "word_id")
    @JsonBackReference
    private Word word;
}
