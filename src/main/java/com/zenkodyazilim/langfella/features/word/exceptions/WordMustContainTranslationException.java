package com.zenkodyazilim.langfella.features.word.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityDomainException;

public class WordMustContainTranslationException extends EntityDomainException {
    public WordMustContainTranslationException() {
        super("Word must contain at least one translation");
    }
}
