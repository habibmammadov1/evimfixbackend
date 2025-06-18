package com.evimfix.evimfix.config.security.jwt;

import com.evimfix.evimfix.dao.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtModel {
    private UUID userId;
    private String email;
    private String username;
    private long refreshTokenExpire;
    private Set<ERole> roles;
    private String jwtToken;

    public JwtModel(UUID userId, String username, String email, long refreshTokenExpire, Set<ERole> roles) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.refreshTokenExpire = refreshTokenExpire;
        this.roles = roles;
    }
}
