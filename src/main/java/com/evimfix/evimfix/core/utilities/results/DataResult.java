package com.evimfix.evimfix.core.utilities.results;


import com.evimfix.evimfix.core.utilities.status.StatusCode;
import lombok.Getter;

@Getter
public class DataResult <T> extends Result {
    private final T data;

    public DataResult(boolean success , StatusCode statusCode, T data) {
        super(success, statusCode);
        this.data = data;
    }
}
