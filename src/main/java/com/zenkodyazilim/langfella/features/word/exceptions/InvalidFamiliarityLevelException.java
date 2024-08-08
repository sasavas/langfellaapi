package com.zenkodyazilim.langfella.features.word.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityValidationException;

public class InvalidFamiliarityLevelException extends EntityValidationException {
    public InvalidFamiliarityLevelException(String givenValue) {
        super("Familiarity Level", givenValue);
    }
}
