package com.evimfix.evimfix.services.abstracts;

import com.evimfix.evimfix.core.utilities.results.DataResult;
import com.evimfix.evimfix.dao.model.request.LoginRequest;
import com.evimfix.evimfix.dao.model.response.UserJwtResponse;

public interface IUserService {
    DataResult<UserJwtResponse> login(LoginRequest loginRequest);
}
