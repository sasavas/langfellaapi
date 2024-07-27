package com.zenkodyazilim.langfella.features.article.entities;

import com.zenkodyazilim.langfella.features.article.exceptions.IllegalLevelCodeException;

import java.util.List;

public record Level(String code, String description) {

    public static final Level A1 = new Level("A1", "Beginner");
    public static final Level A2 = new Level("A2", "Elementary");
    public static final Level B1 = new Level("B1", "Intermediate");
    public static final Level B2 = new Level("B2", "Upper-Intermediate");
    public static final Level C1 = new Level("C1", "Advanced");
    public static final Level C2 = new Level("C2", "Proficient");
    public static final Level Unabdridged = new Level("Unabridged", "Unabridged");

    public static final List<Level> AllLevels = List.of(
            A1, A2, B1, B2, C1, C2, Unabdridged
    );

    public static Level findByLevelCode(String levelCode) {
        return AllLevels.stream()
                .filter(x -> x.code.equals(levelCode))
                .findFirst()
                .orElseThrow(IllegalLevelCodeException::new);
    }
}
