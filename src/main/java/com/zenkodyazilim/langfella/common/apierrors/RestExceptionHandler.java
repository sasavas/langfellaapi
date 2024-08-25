package com.zenkodyazilim.langfella.common.apierrors;

import com.zenkodyazilim.langfella.common.constants.MessageKeys;
import com.zenkodyazilim.langfella.common.exceptions.EntityDomainException;
import com.zenkodyazilim.langfella.common.exceptions.EntityIllegalValueException;
import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @Nonnull HttpMessageNotReadableException ex,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatusCode status,
            @Nonnull WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        logger.warn("Entity not found: {}", ex.getMessage());
        ApiError apiError = new ApiError(NOT_FOUND, ex.getMessage(), MessageKeys.ENTITY_NOT_FOUND);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleDomainValidationException(EntityIllegalValueException ex) {
        logger.warn("Validation failed: {}", ex.getMessage());
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), MessageKeys.ILLEGAL_VALUE);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleDomainValidationException(EntityDomainException ex) {
        logger.warn("Business rule violation: {}", ex.getMessage());
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), MessageKeys.DOMAIN_VALIDATION);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInternalServerError(Exception ex) {
        logger.error("Unexpected internal server error: {}", ex.getMessage(), ex);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred. Please try again later.", MessageKeys.INTERNAL_SERVER_ERROR);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}