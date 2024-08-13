package com.zenkodyazilim.langfella.features.learner.entities;

import com.zenkodyazilim.langfella.common.models.BaseEntity;
import jakarta.persistence.*;
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
            joinColumns = @JoinColumn(name = "learner_id"),
            inverseJoinColumns = @JoinColumn(name = "dictionary_id")
    )
    private List<Dictionary> dictionaries = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "learner_translator",
            joinColumns = @JoinColumn(name = "learner_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private List<Translator> translators = new ArrayList<>();

    private String mainLanguage;

    private List<String> targetLanguages = new ArrayList<>();

    public void updateSubscription(Subscription newSubscription){
        this.subscription = newSubscription;
    }

    public void activateSubscription(){
        this.setSubscriptionActive(true);
    }
}
