package com.evimfix.evimfix.services.abstracts;

import com.evimfix.evimfix.core.utilities.results.DataResult;
import com.evimfix.evimfix.core.utilities.results.Result;
import com.evimfix.evimfix.dao.model.request.CreateAdminRequest;
import com.evimfix.evimfix.dao.model.request.LoginRequest;
import com.evimfix.evimfix.dao.model.response.UserJwtResponse;

public interface IAdminService {
    DataResult<UserJwtResponse> adminLogin(LoginRequest loginRequest);
    Result create(CreateAdminRequest createAdminRequest);
}
