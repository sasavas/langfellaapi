package com.zenkodyazilim.langfella.features.learner.entities;

import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Dictionary extends BaseEntity {
    private String name;
    private String sourceLanguageCode;
    private String targetLanguageCode;
}
