package com.zenkodyazilim.langfella.features.word.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityIllegalValueException;

public class IllegalFamiliarityLevelException extends EntityIllegalValueException {
    public IllegalFamiliarityLevelException(String givenValue) {
        super("Familiarity Level", givenValue);
    }
}
