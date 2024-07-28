package com.zenkodyazilim.langfella.features.word;

import com.zenkodyazilim.langfella.features.word.dtos.CreateWordDTO;
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
        return new ResponseEntity<>(wordService.getWords(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@Valid @RequestBody CreateWordDTO createWordDTO) {
        var word = wordService.createWord(createWordDTO);
        return new ResponseEntity<>(word, HttpStatus.CREATED);
    }
}