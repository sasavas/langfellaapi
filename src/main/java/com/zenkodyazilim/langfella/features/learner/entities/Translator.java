package com.zenkodyazilim.langfella.features.learner.entities;

import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Translator extends BaseEntity {
    private String name;
    private String description;
}
