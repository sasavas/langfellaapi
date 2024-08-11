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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dictionary> dictionaries = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Translator> translators = new ArrayList<>();

    private String mainLanguage;

    private List<String> targetLanguages;

    public void updateSubscription(Subscription newSubscription){
        this.subscription = newSubscription;
    }

    public void activateSubscription(){
        this.setSubscriptionActive(true);
    }
}
