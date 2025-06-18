package com.evimfix.evimfix.exception.model.codes;

import com.evimfix.evimfix.core.utilities.status.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements StatusCode {
    PERMISSION_DENIED(4000, HttpStatus.UNAUTHORIZED,"permission denied"),
    UNEXPECTED_ERROR(4001, HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error"),
    ROLE_NOT_FOUND(4002, HttpStatus.NOT_FOUND, "Role not found"),;

    private final int code;
    private final HttpStatus httpCode;
    private final String message;


}
