package com.zenkodyazilim.langfella.features.learner;

import com.zenkodyazilim.langfella.features.learner.entities.Translator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/learner")
@AllArgsConstructor
public class LearnerController {
    private final LearnerService learnerService;

    @GetMapping("/{id}/translators/{translatorId}")
    ResponseEntity<Void> addTranslator(@PathVariable long id, @PathVariable long translatorId) {
        learnerService.addTranslator(id, translatorId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/translators/{translatorId}")
    ResponseEntity<Void> removeTranslator(@PathVariable long id, @PathVariable long translatorId) {
        learnerService.removeTranslator(id, translatorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/translators")
    ResponseEntity<List<Translator>> getTranslators(){
        var translators = learnerService.getAvailableTranslators();
        return ResponseEntity.ok(translators);
    }
}
