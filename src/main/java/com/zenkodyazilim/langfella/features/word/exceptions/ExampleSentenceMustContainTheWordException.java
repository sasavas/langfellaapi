package com.zenkodyazilim.langfella.features.word.exceptions;

import com.zenkodyazilim.langfella.common.exceptions.EntityValidationException;

public class ExampleSentenceMustContainTheWordException extends EntityValidationException {
    public ExampleSentenceMustContainTheWordException() {
        super("Example sentence must contain the word");
    }
}
