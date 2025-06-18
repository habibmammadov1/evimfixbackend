package com.evimfix.evimfix.core.utilities.status;

import org.springframework.http.HttpStatus;

public interface StatusCode {
    int getCode();
    HttpStatus getHttpCode();
    String getMessage();
}
