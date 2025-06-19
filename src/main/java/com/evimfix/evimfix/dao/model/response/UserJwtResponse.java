package com.evimfix.evimfix.dao.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJwtResponse {
    private String username;
    private String accessToken;
    private Long jwtExpiration;

}
