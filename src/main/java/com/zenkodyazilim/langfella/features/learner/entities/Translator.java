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
@Getter
@Setter
@NoArgsConstructor
public class Translator extends BaseEntity {
    private String name;
    private String description;

    @ManyToMany(mappedBy = "translators")
    @JsonBackReference
    private List<Learner> learners = new ArrayList<>();

    public static Translator of(String name, String description){
        var translator = new Translator();
        translator.setName(name);
        translator.setDescription(description);
        return translator;
    }
}
