package com.evimfix.evimfix.exception.handler;

import com.evimfix.evimfix.core.utilities.response.CustomResponseEntity;
import com.evimfix.evimfix.core.utilities.results.ErrorResult;
import com.evimfix.evimfix.core.utilities.results.Result;
import com.evimfix.evimfix.exception.model.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Result> baseException(BaseException e) {
        return new CustomResponseEntity<>(new ErrorResult(e.getStatusCode(), e.getMessage()));
    }
}
