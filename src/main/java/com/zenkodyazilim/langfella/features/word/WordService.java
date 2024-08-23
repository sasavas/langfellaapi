package com.zenkodyazilim.langfella.features.word;

import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import com.zenkodyazilim.langfella.features.word.dtos.CreateWordDTO;
import com.zenkodyazilim.langfella.features.word.dtos.UpdateWordDTO;
import com.zenkodyazilim.langfella.features.word.entities.ExampleSentence;
import com.zenkodyazilim.langfella.features.word.entities.Translation;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Word createWord(CreateWordDTO createWordDTO) {
        Word word = new Word(
                createWordDTO.text(),
                createWordDTO.sourceLanguageCode(),
                createWordDTO.targetLanguageCode(),
                createWordDTO.familiarity().orElse(0));

        word.setTranslations(createWordDTO.translations().stream().map(Translation::new).collect(Collectors.toSet()));

        if (createWordDTO.articleId().isPresent()) {
            word.setArticleId(createWordDTO.articleId().get());
        }

        if (createWordDTO.chapterId().isPresent()) {
            word.setChapterId(createWordDTO.chapterId().get());
        }

        if (createWordDTO.exampleSentence().isPresent()) {
            word.setExampleSentences(Set.of(new ExampleSentence(createWordDTO.exampleSentence().get())));
        }

        return wordRepository.save(word);
    }

    @Transactional
    public Word updateWord(long id, UpdateWordDTO updateWordDTO) {
        var word = wordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Word.class.getSimpleName(), String.valueOf(id)));

        updateWordDTO.familiarity().ifPresent(word::setFamiliarity);

        updateWordDTO.translationsToAdd().ifPresent((translations) ->
                word.getTranslations().addAll(
                        translations.stream().map(Translation::new).collect(Collectors.toSet())));

        updateWordDTO.exampleSentencesToAdd().ifPresent((exampleSentences) ->
                word.getExampleSentences().addAll(
                        exampleSentences.stream().map(ExampleSentence::new).collect(Collectors.toSet())));

        return wordRepository.save(word);
    }

    public void deleteWord(long id) {
        var word = wordRepository.getReferenceById(id);
        wordRepository.delete(word);
    }

    public List<Word> getWordsByArticleId(long articleId) {
        return wordRepository.findByArticleId(articleId);
    }
}
