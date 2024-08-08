package com.zenkodyazilim.langfella.features.word.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityDomainException;

public class ExampleSentenceMustContainTheWordException extends EntityDomainException {
    public ExampleSentenceMustContainTheWordException() {
        super("Example sentence must contain the word");
    }
}
