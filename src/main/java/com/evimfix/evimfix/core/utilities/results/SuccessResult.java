package com.evimfix.evimfix.core.utilities.results;

import com.evimfix.evimfix.core.utilities.status.StatusCode;

public class SuccessResult extends Result {

    public SuccessResult(StatusCode statusCode) {
        super(true, statusCode);
    }

}
