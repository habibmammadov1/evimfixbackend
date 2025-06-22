package com.evimfix.evimfix.config.security.services;

import com.evimfix.evimfix.config.security.jwt.JwtModel;
import com.evimfix.evimfix.dao.model.enums.ERole;
import com.evimfix.evimfix.exception.model.BaseException;
import com.evimfix.evimfix.exception.model.codes.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${action.app.jwtSecret}")
    private String jwtSecret;

    @Value("${action.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private SecretKey getSigningKey() {
        if (jwtSecret.getBytes().length < 32) {
            throw new IllegalArgumentException("JWT Secret key must be at least 32 bytes long for HS512.");
        }
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public Claims parseJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateJwtToken(JwtModel jwtModel) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        JwtBuilder jwt = Jwts.builder()
                .setSubject(jwtModel.getEmail())
                .claim("userId", jwtModel.getUserId().toString())
                .claim("username", jwtModel.getUsername())
                .claim("roles", jwtModel.getRoles().stream().map(Enum::name).toList())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512);

        return jwt.compact();
    }

    public JwtModel getJwtModelFromToken(String token) {
        Claims claims = parseJwtToken(token);

        Long userId = Long.parseLong(claims.get("userId", String.class));
        String username = claims.get("username", String.class);
        String email = claims.getSubject();


        Object rolesObj = claims.get("roles");


        List<String> roles = rolesObj instanceof List<?>
                ? (List<String>) rolesObj
                : Collections.emptyList();


        Set<ERole> roleSet = roles.stream().map(r -> switch (r) {
            case "ROLE_USER" -> ERole.ROLE_USER;
            case "ROLE_ADMIN" -> ERole.ROLE_ADMIN;
            case "ROLE_SUPER_ADMIN" -> ERole.ROLE_SUPER_ADMIN;
            default ->
                    throw new BaseException(ErrorCode.UNKNOWN_ROLE);
        }).collect(Collectors.toSet());

        return new JwtModel(userId, email, username, claims.getExpiration().getTime(), roleSet, token);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
