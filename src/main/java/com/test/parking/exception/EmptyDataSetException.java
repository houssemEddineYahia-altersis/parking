package com.test.parking.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.test.parking.exception.errors.CustomHttpStatus;

public class EmptyDataSetException extends RuntimeException {
    @JsonCreator
    public EmptyDataSetException() {
        super(CustomHttpStatus.EMPTY_DATA_SET.reason());
    }
}
