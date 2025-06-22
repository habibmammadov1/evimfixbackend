package com.evimfix.evimfix.exception.model.codes;

import com.evimfix.evimfix.core.utilities.status.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements StatusCode {
    SUCCESS(1000, HttpStatus.CREATED,"process successful completed"),
    ADMIN_SUCCESSFULLY_LOGGED_IN(2000, HttpStatus.OK, "admin successfully logged in"),
    ADMIN_SUCCESSFULLY_CREATED(2000, HttpStatus.CREATED, "admin successfully created"),;

    private final int code;
    private final HttpStatus httpCode;
    private final String message;

}
