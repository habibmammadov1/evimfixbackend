package com.evimfix.evimfix.core.utilities.results;

import com.evimfix.evimfix.core.utilities.status.StatusCode;

public class ErrorDataResult<T> extends DataResult<T> {

    public ErrorDataResult(StatusCode statusCode, String message, T data) {
        super(false, statusCode, data);
    }

}
