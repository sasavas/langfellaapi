package com.zenkodyazilim.langfella.features.translation;

import com.zenkodyazilim.langfella.features.translation.dtos.TranslationDTO;
import com.zenkodyazilim.langfella.features.translation.dtos.TranslationRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/translations")
@AllArgsConstructor
public class TranslationController {
    private final TranslationService translationService;

    @PostMapping
    public ResponseEntity<Set<TranslationDTO>> translate(@Valid @RequestBody TranslationRequest translationRequest) {
        //TODO
        var learnerId = 1L;

        var result = translationService.translate(learnerId, translationRequest);
        return ResponseEntity.ok(result);
    }
}
