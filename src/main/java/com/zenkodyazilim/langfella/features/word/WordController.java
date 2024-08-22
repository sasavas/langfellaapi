package com.zenkodyazilim.langfella.features.word;

import com.zenkodyazilim.langfella.features.word.dtos.CreateWordDTO;
import com.zenkodyazilim.langfella.features.word.dtos.UpdateWordDTO;
import com.zenkodyazilim.langfella.features.word.entities.Word;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {
    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public ResponseEntity<List<Word>> getWords() {
        return ResponseEntity.ok(wordService.getWords());
    }

    @GetMapping("{id}")
    public ResponseEntity<Word> getWordById(@PathVariable("id") long id) {
        return ResponseEntity.ok(wordService.getWordById(id));
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@Valid @RequestBody CreateWordDTO createWordDTO) {
        var word = wordService.createWord(createWordDTO);
        return new ResponseEntity<>(word, HttpStatus.CREATED);
    }

    //TODO Update this endpoint to accept both adding and updating (combine with createWord endpoint)
    // in the service, if the word already exists then just add the new translations and example sentences.
    @PutMapping("{id}")
    public ResponseEntity<Word> updateWord(@PathVariable("id") long id,
                                           @Valid @RequestBody UpdateWordDTO updateWordDTO) {
        var updatedWord = wordService.updateWord(id, updateWordDTO);
        return ResponseEntity.ok(updatedWord);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable("id") long id) {
        wordService.deleteWord(id);
        return ResponseEntity.noContent().build();
    }
}