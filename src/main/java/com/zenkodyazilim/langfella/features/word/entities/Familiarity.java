package com.zenkodyazilim.langfella.features.word.entities;

import com.zenkodyazilim.langfella.features.word.exceptions.IllegalFamiliarityException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Familiarity {
    NEW(0),
    FAMILIAR(1),
    KNOWN(2);

    private final int level;

    Familiarity(int level) {
        this.level = level;
    }

    public static Familiarity fromInt(int level) {
        return Arrays.stream(Familiarity.values())
                .filter(l -> l.level == level)
                .findFirst()
                .orElseThrow(() -> new IllegalFamiliarityException(String.valueOf(level)));
    }
}