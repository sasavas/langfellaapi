package com.zenkodyazilim.langfella.features.word.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Translation extends BaseEntity {
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Word word;

    public Translation(String text) {
        this.setText(text);
    }
}
