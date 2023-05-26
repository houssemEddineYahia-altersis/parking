package com.test.parking.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.test.parking.exception.errors.CustomHttpStatus;

public class IllegalDataInput extends RuntimeException {
    @JsonCreator
    public IllegalDataInput() { super(CustomHttpStatus.ILLEGAL_DATA_INPUT.reason());
    }
}
