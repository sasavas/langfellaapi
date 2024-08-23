package com.zenkodyazilim.langfella.common.exceptions;

import lombok.Getter;

/**
 * When the given value is illegal
 */
@Getter
public class EntityIllegalValueException extends LangfellaException {
    private final String object;
    private final String givenValue;

    public EntityIllegalValueException(String object, String givenValue) {
        super(String.format("Invalid value {%s} for the object {%s}", givenValue, object));
        this.object = object;
        this.givenValue = givenValue;
    }
}
