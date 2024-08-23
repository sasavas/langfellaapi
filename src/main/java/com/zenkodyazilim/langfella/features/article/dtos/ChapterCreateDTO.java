package com.zenkodyazilim.langfella.features.article.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record ChapterCreateDTO(
        Optional<String> title,
        @NotNull String storyLine) {
}