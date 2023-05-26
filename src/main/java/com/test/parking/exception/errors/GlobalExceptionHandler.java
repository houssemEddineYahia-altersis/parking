package com.test.parking.exception.errors;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.test.parking.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("code", status);
        body.put("status", status.value());
        body.put("error", error);
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>(0);
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ApiError apiError = new ApiError(CustomHttpStatus.BAD_REQUEST, new Date(), errors);
        return new ResponseEntity<>(apiError, headers, status);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus status) {
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<Object> configurationItemExistException(CityAlreadyExistsException ex) {
        return buildResponseEntity(new ApiError(CustomHttpStatus.CITY_ALREADY_EXISTS, new Date(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyDataSetException.class)
    public ResponseEntity<Object> emptyDataSetException(EmptyDataSetException ex) {
        return buildResponseEntity(new ApiError(CustomHttpStatus.EMPTY_DATA_SET, new Date(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Object> cityNotFoundException(CityNotFoundException ex) {
        return buildResponseEntity(new ApiError(CustomHttpStatus.CITY_NOT_FOUND, new Date(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalDataInput.class)
    public ResponseEntity<Object> illegalDataInputException(IllegalDataInput ex) {
        return buildResponseEntity(new ApiError(CustomHttpStatus.ILLEGAL_DATA_INPUT, new Date(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyThirdPartyResultException.class)
    public ResponseEntity<Object> emptyThirdPartyResultException(EmptyThirdPartyResultException ex) {
        return buildResponseEntity(new ApiError(CustomHttpStatus.EMPTY_THIRD_PART, new Date(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }


}