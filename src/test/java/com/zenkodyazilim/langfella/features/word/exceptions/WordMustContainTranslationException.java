package com.zenkodyazilim.langfella.features.word.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityValidationException;

public class WordMustContainTranslationException extends EntityValidationException {
    public WordMustContainTranslationException() {
        super("Word must contain at least one translation");
    }
}
