package com.postalservice.exceptions;

public class PostalItemAlreadyReceivedException extends RuntimeException {

    public PostalItemAlreadyReceivedException(String message) {
        super(message);
    }

}
