package com.zenkodyazilim.langfella.common.apierrors;

import com.zenkodyazilim.langfella.common.constants.MessageKeys;
import com.zenkodyazilim.langfella.common.exceptions.DomainValidationException;
import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage()); // which message?
        apiError.setMessageKey(MessageKeys.ENTITY_NOT_FOUND);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleDomainValidationException(DomainValidationException ex){
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage()); // the message we got in the exception class
        apiError.setMessageKey(MessageKeys.DOMAIN_VALIDATION);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}