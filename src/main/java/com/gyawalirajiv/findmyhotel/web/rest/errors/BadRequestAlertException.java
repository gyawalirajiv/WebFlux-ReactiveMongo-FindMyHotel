package com.gyawalirajiv.findmyhotel.web.rest.errors;

public class BadRequestAlertException extends RuntimeException{

    public BadRequestAlertException(String message) {
        super(message);
    }
}
