package com.test.parking.exception.errors;

import org.springframework.lang.Nullable;

public enum CustomHttpStatus {

    BAD_REQUEST(900, "Bad request"),
    CITY_NOT_FOUND(901, "City not found request"),
    CITY_ALREADY_EXISTS(902, "City already exists"),
    EMPTY_DATA_SET(903, "Data set is empty"),
    ILLEGAL_DATA_INPUT(904, "Available parking list could not be bigger than parking list"),
    EMPTY_THIRD_PART(905, "Cannot get data from external API");
    private final int value;
    private final String reason;

    CustomHttpStatus(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public int value() {
        return this.value;
    }

    public String reason() {
        return this.reason;
    }

    @Override
    public String toString() {
        return this.value + " " + name();
    }

    public static CustomHttpStatus valueOf(int statusCode) {
        CustomHttpStatus status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        }
        return status;
    }

    @Nullable
    public static CustomHttpStatus resolve(int statusCode) {
        for (CustomHttpStatus status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
