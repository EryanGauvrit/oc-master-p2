package com.p3openclassrooms.p3oc.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.p3openclassrooms.p3oc.dto.ErrorEntity;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public @ResponseBody ErrorEntity handleNotFoundException(EntityNotFoundException e) {
        return new ErrorEntity(e.getMessage(), HttpStatus.NOT_FOUND.toString());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public @ResponseBody ErrorEntity handleRuntimeException(RuntimeException e) {
        return new ErrorEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({SecurityException.class, BadCredentialsException.class})
    public @ResponseBody ErrorEntity handleSecurityException(SecurityException e) {
        return new ErrorEntity(e.getMessage(), HttpStatus.FORBIDDEN.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public @ResponseBody ErrorEntity handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorEntity(e.getMessage(), HttpStatus.BAD_REQUEST.toString());
    }

}
