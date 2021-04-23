package com.gyawalirajiv.findmyhotel.web.rest.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(BadRequestAlertException.class)
    public ResponseEntity<ExceptionResponse> badRequestHandler(BadRequestAlertException exception){
        ExceptionResponse response = new ExceptionResponse(exception.getMessage(), Instant.now());
        return ResponseEntity.badRequest().body(response);
    }

}

