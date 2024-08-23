package com.zenkodyazilim.langfella.features.article.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityIllegalValueException;

public class IllegalLevelCodeException extends EntityIllegalValueException {
    public IllegalLevelCodeException(String givenValue) {
        super("Level", givenValue);
    }
}
