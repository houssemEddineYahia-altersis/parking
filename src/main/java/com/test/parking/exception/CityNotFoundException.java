package com.test.parking.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.test.parking.exception.errors.CustomHttpStatus;

public class CityNotFoundException extends RuntimeException {
    @JsonCreator
    public CityNotFoundException() {
        super(CustomHttpStatus.CITY_NOT_FOUND.reason());
    }
}
