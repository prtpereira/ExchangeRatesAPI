package com.bvgroup.exchangerates.handlers;

import com.bvgroup.exchangerates.exceptions.CustomRateException;
import com.bvgroup.exchangerates.representers.CustomExceptionRepresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    private final static String ERROR_RESPONSE_500 = "An error occurred while trying to compute your request...";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionRepresenter> ExceptionHandler(Exception e) {
        return ResponseEntity.internalServerError().body(
                new CustomExceptionRepresenter("internal_server_error", ERROR_RESPONSE_500, e.getMessage())
        );
    }

    @ExceptionHandler(value = CustomRateException.class)
    public ResponseEntity<CustomExceptionRepresenter> handleBlogAlreadyExistsException(CustomRateException e) {
        return ResponseEntity.badRequest().body(
                new CustomExceptionRepresenter(e.getType(), e.getMessage())
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CustomExceptionRepresenter> handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(
                new CustomExceptionRepresenter("missing_params", e.getMessage())
        );
    }

}