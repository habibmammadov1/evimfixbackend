package com.evimfix.evimfix.core.utilities.results;

import com.evimfix.evimfix.core.utilities.status.StatusCode;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(StatusCode statusCode, T data) {
        super(true, statusCode, data);
    }

}
