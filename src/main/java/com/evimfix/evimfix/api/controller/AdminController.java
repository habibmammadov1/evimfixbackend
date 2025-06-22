package com.evimfix.evimfix.api.controller;

import com.evimfix.evimfix.core.utilities.response.CustomResponseEntity;
import com.evimfix.evimfix.core.utilities.results.DataResult;
import com.evimfix.evimfix.core.utilities.results.Result;
import com.evimfix.evimfix.dao.model.request.CreateAdminRequest;
import com.evimfix.evimfix.dao.model.request.LoginRequest;
import com.evimfix.evimfix.dao.model.response.UserJwtResponse;
import com.evimfix.evimfix.services.abstracts.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class AdminController {

    private final IAdminService adminService;


    @PostMapping("/sign-in")
    public ResponseEntity<DataResult<UserJwtResponse>> login(@RequestBody LoginRequest loginRequest) {
        return new CustomResponseEntity<>(adminService.adminLogin(loginRequest));
    }

    @PostMapping("/admins")
    public ResponseEntity<Result> create(@RequestBody CreateAdminRequest createAdminRequest){
        return new CustomResponseEntity<>(adminService.create(createAdminRequest));
    }
}
