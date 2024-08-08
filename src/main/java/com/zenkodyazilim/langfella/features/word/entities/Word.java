package com.zenkodyazilim.langfella.features.word.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zenkodyazilim.langfella.features.word.exceptions.ExampleSentenceMustContainTheWordException;
import com.zenkodyazilim.langfella.features.word.exceptions.WordMustContainTranslationException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;
    private String sourceLanguageCode;
    private String targetLanguageCode;
    private Familiarity familiarity;
    private Long articleId;
    private Long chapterId;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Translation> translations = new HashSet<>();

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ExampleSentence> exampleSentences = new HashSet<>();

    public Word(
            String text,
            String sourceLanguageCode,
            String targetLanguageCode,
            int familiarity) {
        this.text = text;
        this.sourceLanguageCode = sourceLanguageCode;
        this.targetLanguageCode = targetLanguageCode;
        this.familiarity = Familiarity.fromInt(familiarity);
    }

    public void setTranslations(Set<Translation> translations){
        if(translations.isEmpty()){
            throw new WordMustContainTranslationException();
        }

        this.translations = translations;
    }

    public void setExampleSentences(Set<ExampleSentence> exampleSentences){
        if(!exampleSentences.stream().allMatch(p -> p.getText().contains(this.getText()))){
            throw new ExampleSentenceMustContainTheWordException();
        }

        this.exampleSentences = exampleSentences;
    }
}
