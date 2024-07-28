package com.zenkodyazilim.langfella.features.word;

import com.zenkodyazilim.langfella.features.word.dtos.CreateWordDTO;
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

    public List<Word> getWords(){
        return wordRepository.findAll();
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
}
