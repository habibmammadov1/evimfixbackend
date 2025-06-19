package com.evimfix.evimfix.api.controller;

import com.evimfix.evimfix.core.utilities.response.CustomResponseEntity;
import com.evimfix.evimfix.core.utilities.results.DataResult;
import com.evimfix.evimfix.dao.model.request.LoginRequest;
import com.evimfix.evimfix.dao.model.response.UserJwtResponse;
import com.evimfix.evimfix.services.abstracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final IUserService userService;


    @PostMapping("/sign-in")
    public ResponseEntity<DataResult<UserJwtResponse>> login(LoginRequest loginRequest) {
        return new CustomResponseEntity<>(userService.login(loginRequest));
    }

}
