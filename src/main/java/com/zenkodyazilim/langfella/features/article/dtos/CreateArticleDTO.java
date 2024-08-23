package com.zenkodyazilim.langfella.features.article.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public record CreateArticleDTO(
        @NotNull String languageCode,
        Optional<String> title,
        Optional<String> levelCode,
        Optional<List<AuthorDTO>> authors,
        @NotNull @NotEmpty List<ChapterCreateDTO> chapters) {
}