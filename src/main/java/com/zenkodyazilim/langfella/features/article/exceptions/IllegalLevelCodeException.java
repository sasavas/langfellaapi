package com.zenkodyazilim.langfella.features.article.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityValidationException;

public class IllegalLevelCodeException extends EntityValidationException {
    public IllegalLevelCodeException(String givenValue) {
        super("Level", givenValue);
    }
}
