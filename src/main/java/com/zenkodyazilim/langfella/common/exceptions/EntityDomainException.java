package com.zenkodyazilim.langfella.common.exceptions;

/**
 * When a business logic within the domain fails
 */
public class EntityDomainException extends LangfellaException{
    public EntityDomainException(String message) {
        super(message);
    }
}
