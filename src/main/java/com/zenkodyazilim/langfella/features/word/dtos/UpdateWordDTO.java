package com.zenkodyazilim.langfella.features.word.dtos;

import com.zenkodyazilim.langfella.features.word.entities.Familiarity;

import java.util.Optional;
import java.util.Set;

public record UpdateWordDTO(
        Optional<Familiarity> familiarity,
        Set<String> translationsToAdd,
        Set<String> exampleSentencesToAdd) {
}
