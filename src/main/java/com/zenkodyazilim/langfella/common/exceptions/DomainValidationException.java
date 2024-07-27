package com.zenkodyazilim.langfella.common.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainValidationException extends RuntimeException {
    private String object;
    private String message;

    public DomainValidationException(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
