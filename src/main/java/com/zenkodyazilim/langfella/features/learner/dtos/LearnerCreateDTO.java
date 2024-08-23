package com.zenkodyazilim.langfella.features.learner.dtos;

import com.zenkodyazilim.langfella.features.learner.entities.Subscription;
import jakarta.validation.constraints.NotNull;

public record LearnerCreateDTO(
        @NotNull long userId,
        @NotNull String mainLanguage,
        @NotNull Subscription subscription) {
}
