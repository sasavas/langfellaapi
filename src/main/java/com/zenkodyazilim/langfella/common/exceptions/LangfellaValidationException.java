package com.zenkodyazilim.langfella.common.exceptions;

import lombok.Getter;

@Getter
public class LangfellaValidationException extends LangfellaException {
    private final String object;
    private final String givenValue;

    public LangfellaValidationException(String object, String givenValue) {
        super(String.format("Invalid value {%s} for the object {%s}", givenValue, object));
        this.object = object;
        this.givenValue = givenValue;
    }
}
