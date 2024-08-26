package com.zenkodyazilim.langfella.features.learner.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Dictionary extends BaseEntity {
    private String name;
    private String sourceLanguageCode;
    private String targetLanguageCode;

    @ManyToMany(mappedBy = "dictionaries")
    @JsonBackReference
    private List<Learner> learners = new ArrayList<>();
}
