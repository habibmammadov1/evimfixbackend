package com.evimfix.evimfix.exception.model.codes;

import com.evimfix.evimfix.core.utilities.status.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements StatusCode {
    SUCCESS(1000, HttpStatus.CREATED,"process successful completed"),;

    private final int code;
    private final HttpStatus httpCode;
    private final String message;

}
