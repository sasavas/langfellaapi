package com.zenkodyazilim.langfella.features.article.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.LangfellaValidationException;

public class IllegalLevelCodeException extends LangfellaValidationException {
    public IllegalLevelCodeException(String givenValue) {
        super("Level", givenValue);
    }
}
