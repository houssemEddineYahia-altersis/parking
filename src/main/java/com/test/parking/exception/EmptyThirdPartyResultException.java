package com.test.parking.exception;

import com.test.parking.exception.errors.CustomHttpStatus;

/**
 * Data access exception thrown when a result was expected to have at least
 * one row (or element) but zero rows (or elements) were actually returned.
 *
 */
public class EmptyThirdPartyResultException extends RuntimeException {

    public EmptyThirdPartyResultException() {
        super(CustomHttpStatus.EMPTY_THIRD_PART.reason());
    }



}

