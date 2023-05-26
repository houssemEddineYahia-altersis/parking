package com.test.parking.exception;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AlreadyExistsException extends RuntimeException {
    @JsonCreator
    public AlreadyExistsException(String message) {
        super(message);
    }
}
