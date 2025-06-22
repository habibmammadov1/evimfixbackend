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
    ROLE_NOT_FOUND(4002, HttpStatus.NOT_FOUND, "Role not found"),
    USER_NOT_FOUND(4003, HttpStatus.NOT_FOUND, "User not found"),
    USERNAME_OR_PASSWORD_INCORRECT(4004, HttpStatus.BAD_REQUEST, "Username or password incorrect"),
    UNKNOWN_ROLE(4005, HttpStatus.INTERNAL_SERVER_ERROR, "Unknown role"),
    USER_ALREADY_EXISTS(4006, HttpStatus.BAD_REQUEST, "User already exists"),
    PASSWORD_CANNOT_BE_EMPTY(4007, HttpStatus.BAD_REQUEST, "Password cannot be empty"),;

    private final int code;
    private final HttpStatus httpCode;
    private final String message;


}
