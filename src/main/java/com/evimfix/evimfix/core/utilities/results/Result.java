package com.evimfix.evimfix.core.utilities.results;

import com.evimfix.evimfix.core.utilities.status.StatusCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;


@Getter
public class Result{

    private final boolean success;
    private final int statusCode;
    private final String message;
    @JsonIgnore
    private final StatusCode statusCodeObject;

    public Result(boolean success, StatusCode statusCode){
        this.success = success;
        this.message = statusCode.getMessage();
        this.statusCode = statusCode.getCode();
        this.statusCodeObject = statusCode;
    }

    public Result(boolean success, StatusCode statusCode, String message){
        this.success = success;
        this.message = message;
        this.statusCode = statusCode.getCode();
        this.statusCodeObject = statusCode;
    }



}
