package com.test.parking.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Data access exception thrown when a result was expected to have at least
 * one row (or element) but zero rows (or elements) were actually returned.
 *
 */
public class EmptyThirdPartyResultException extends NestedRuntimeException {

    public EmptyThirdPartyResultException(String message) {
        super(message);
    }

    public EmptyThirdPartyResultException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

