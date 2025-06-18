package com.evimfix.evimfix.exception.model;

import com.evimfix.evimfix.core.utilities.status.StatusCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final StatusCode statusCode;

    public BaseException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }

    public BaseException(StatusCode statusCode, Exception e) {
        super(e.getMessage());
        this.statusCode = statusCode;
    }
}
