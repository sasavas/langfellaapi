package com.zenkodyazilim.langfella.domainTests.word;

import com.zenkodyazilim.langfella.features.word.entities.ExampleSentence;
import com.zenkodyazilim.langfella.features.word.entities.Familiarity;
import com.zenkodyazilim.langfella.features.word.entities.Translation;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import com.zenkodyazilim.langfella.features.word.exceptions.ExampleSentenceMustContainTheWordException;
import com.zenkodyazilim.langfella.features.word.exceptions.InvalidFamiliarityLevelException;
import com.zenkodyazilim.langfella.features.word.exceptions.WordMustContainTranslationException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WordTest {

    @Test
    public void testCreateWord() {
        Word word = new Word(
                "word",
                "en",
                "fr",
                Familiarity.NEW.ordinal()
        );
        word.setTranslations(Set.of(new Translation("mot")));
        word.setExampleSentences(Set.of(new ExampleSentence("This is an example sentence containing the word.")));

        assertNotNull(word);
        assertEquals("word", word.getText());
        assertEquals("en", word.getSourceLanguageCode());
        assertEquals("fr", word.getTargetLanguageCode());
        assertEquals(Familiarity.NEW, word.getFamiliarity());
        assertNotNull(word.getTranslations());
        assertEquals(1, word.getTranslations().size());

        assertEquals("mot", word.getTranslations().iterator().next().getText());
        assertNotNull(word.getExampleSentences());
        assertEquals(1, word.getExampleSentences().size());
        assertEquals("This is an example sentence containing the word.", word.getExampleSentences().iterator().next().getText());
    }

    @Test
    public void testExampleSentenceContainsWord() {
        Word word = new Word("word", "en", "fr", Familiarity.NEW.ordinal());
        ExampleSentence exampleSentence = new ExampleSentence("This is an example sentence containing the word.");
        word.setExampleSentences(Set.of(exampleSentence));

        assertNotNull(word.getExampleSentences());
        assertEquals(1, word.getExampleSentences().size());
        assertEquals("This is an example sentence containing the word.", exampleSentence.getText());
        assertEquals("word", word.getText());
        assertTrue(exampleSentence.getText().contains(word.getText()));
    }

    @Test
    public void testExampleSentenceNotContainsWordThrowException() {
        Word word = new Word("word", "en", "fr", Familiarity.NEW.ordinal());
        ExampleSentence exampleSentence = new ExampleSentence("This is an example sentence not containing the ...");

        assertThrows(ExampleSentenceMustContainTheWordException.class, () -> {
            word.setExampleSentences(Set.of(exampleSentence));
        });
    }

    @Test
    public void testCreateWordThrowsExceptionForInvalidFamiliarityLevel() {
        assertThrows(InvalidFamiliarityLevelException.class, () ->
                new Word("word", "en", "fr", 4));
    }

    @Test
    public void testCreateWordWithNoTranslationThrowsException() {
        assertThrows(WordMustContainTranslationException.class, () -> {
            Word word = new Word("word", "en", "fr", Familiarity.NEW.ordinal());
            word.setTranslations(Collections.emptySet());
        });
    }
}
