package com.zenkodyazilim.langfella.common.exceptions;

public class EntityNotFoundException extends LangfellaException {
    public EntityNotFoundException(String entityName, String givenKey) {
        super(entityName + " not found by the given key " + givenKey);
    }
}
