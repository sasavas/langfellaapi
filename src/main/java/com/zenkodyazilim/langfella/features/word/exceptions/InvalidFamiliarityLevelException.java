package com.zenkodyazilim.langfella.features.word.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.LangfellaValidationException;

public class InvalidFamiliarityLevelException extends LangfellaValidationException {
    public InvalidFamiliarityLevelException(String givenValue) {
        super("Familiarity Level", givenValue);
    }
}
