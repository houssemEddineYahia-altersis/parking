package com.test.parking.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.test.parking.exception.errors.CustomHttpStatus;

public class CityAlreadyExistsException extends RuntimeException {
    @JsonCreator
    public CityAlreadyExistsException() {
        super(CustomHttpStatus.CITY_ALREADY_EXISTS.reason());
    }
}
