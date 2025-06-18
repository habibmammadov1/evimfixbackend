package com.evimfix.evimfix.core.utilities.response;

import com.evimfix.evimfix.core.utilities.results.Result;
import org.springframework.http.ResponseEntity;

public class CustomResponseEntity<T extends Result> extends ResponseEntity<T> {
    public CustomResponseEntity(T result) {
        super(result, result.getStatusCodeObject().getHttpCode());
    }
}
