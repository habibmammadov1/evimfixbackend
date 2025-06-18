package com.evimfix.evimfix.core.utilities.results;

import com.evimfix.evimfix.core.utilities.status.StatusCode;

public class ErrorResult extends Result {

    public ErrorResult(StatusCode statusCode) {
        super(false, statusCode);
    }

    public ErrorResult(StatusCode statusCode, String message) {
        super(false, statusCode, message);
    }

}
