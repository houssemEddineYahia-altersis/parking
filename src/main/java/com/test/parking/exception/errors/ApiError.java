package com.test.parking.exception.errors;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int code;
    private CustomHttpStatus status;
    private List<String> errors;

    public ApiError(CustomHttpStatus status, Date timestamp, List<String> errors) {
        this.timestamp = timestamp;
        this.code = status.value();
        this.status = status;
        this.errors = errors;
    }

    public ApiError(CustomHttpStatus status, Date timestamp, String error) {
        this.timestamp = timestamp;
        this.code = status.value();
        this.status = status;
        errors = Collections.singletonList(error);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getCode() {
        return code;
    }

    public CustomHttpStatus getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(CustomHttpStatus status) {
        this.status = status;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
