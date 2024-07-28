package com.zenkodyazilim.langfella.features.word.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.LangfellaValidationException;

public class IllegalFamiliarityException extends LangfellaValidationException {
    public IllegalFamiliarityException(String givenValue) {
        super("Familiarity", givenValue);
    }
}
