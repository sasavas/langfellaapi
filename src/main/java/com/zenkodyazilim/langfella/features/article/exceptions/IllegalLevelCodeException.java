package com.zenkodyazilim.langfella.features.article.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.DomainValidationException;

public class IllegalLevelCodeException extends DomainValidationException {
    public IllegalLevelCodeException(String givenValue) {
        super("Level", givenValue);
    }
}
