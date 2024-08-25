package com.zenkodyazilim.langfella.features.learner.entities;

import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Learner extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Subscription subscription;

    private boolean subscriptionActive;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "learner_dictionary",
            joinColumns = @JoinColumn(name = "LEARNER_ID"),
            inverseJoinColumns = @JoinColumn(name = "DICTIONARY_ID")
    )
    private List<Dictionary> dictionaries = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "learner_translator",
            joinColumns = @JoinColumn(name = "LEARNER_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSLATOR_ID")
    )
    @Setter(AccessLevel.NONE)
    private List<Translator> translators = new ArrayList<>();

    private void addTranslator(Translator translator){
        this.translators.add(translator);
    }

    private void removeTranslator(long id){
        this.translators.removeIf(t -> t.getId() == id);
    }

    private String mainLanguage;

    private List<String> targetLanguages = new ArrayList<>();

    public void updateSubscription(Subscription newSubscription){
        this.subscription = newSubscription;
    }

    public void activateSubscription(){
        this.setSubscriptionActive(true);
    }
}
