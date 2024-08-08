package com.zenkodyazilim.langfella.features.word;

import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import com.zenkodyazilim.langfella.features.word.dtos.CreateWordDTO;
import com.zenkodyazilim.langfella.features.word.dtos.UpdateWordDTO;
import com.zenkodyazilim.langfella.features.word.entities.ExampleSentence;
import com.zenkodyazilim.langfella.features.word.entities.Translation;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getWords() {
        return wordRepository.findAll();
    }

    public Word getWordById(long id) {
        return wordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Word.class.getSimpleName(), String.valueOf(id)));
    }

    public Word createWord(CreateWordDTO createWordDTO) {
        Word word = new Word(
                createWordDTO.text(),
                createWordDTO.sourceLanguageCode(),
                createWordDTO.targetLanguageCode(),
                createWordDTO.familiarity());

        var translations = createWordDTO.translations().stream().map(Translation::new).collect(Collectors.toSet());
        translations.forEach(t -> t.setWord(word));
        word.setTranslations(translations);

        if (!createWordDTO.exampleSentence().isEmpty()) {
            var sentence = new ExampleSentence(createWordDTO.exampleSentence());
            sentence.setWord(word);
            word.setExampleSentences(Set.of(sentence));
        }

        return wordRepository.save(word);
    }

    public Word updateWord(long id, UpdateWordDTO updateWordDTO) {
        var word = wordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Word.class.getSimpleName(), String.valueOf(id)));

        updateWordDTO.familiarity().ifPresent(word::setFamiliarity);

        if (updateWordDTO.translationsToAdd() != null && !updateWordDTO.translationsToAdd().isEmpty()) {
            word.getTranslations().addAll(
                    updateWordDTO.translationsToAdd().stream()
                            .map(Translation::new)
                            .collect(Collectors.toSet()));
        }

        if (updateWordDTO.exampleSentencesToAdd() != null && !updateWordDTO.exampleSentencesToAdd().isEmpty()) {
            word.getExampleSentences().addAll(
                    updateWordDTO.exampleSentencesToAdd().stream()
                            .map(ExampleSentence::new)
                            .collect(Collectors.toSet())
            );
        }

        return wordRepository.save(word);
    }

    public void deleteWord(long id) {
        var word = wordRepository.getReferenceById(id);
        wordRepository.delete(word);
    }
}
