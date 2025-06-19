package com.evimfix.evimfix.services.concretes;

import com.evimfix.evimfix.config.security.jwt.JwtModel;
import com.evimfix.evimfix.config.security.services.JwtUtils;
import com.evimfix.evimfix.core.utilities.results.DataResult;
import com.evimfix.evimfix.core.utilities.results.SuccessDataResult;
import com.evimfix.evimfix.dao.entites.concretes.user.User;
import com.evimfix.evimfix.dao.model.enums.ERole;
import com.evimfix.evimfix.dao.model.request.LoginRequest;
import com.evimfix.evimfix.dao.model.response.UserJwtResponse;
import com.evimfix.evimfix.dao.repository.UserRepository;
import com.evimfix.evimfix.exception.model.BaseException;
import com.evimfix.evimfix.exception.model.codes.ErrorCode;
import com.evimfix.evimfix.exception.model.codes.SuccessCode;
import com.evimfix.evimfix.services.abstracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Value("${action.app.jwtExpirationMs}")
    private Long jwtExpiration;


    @Override
    public DataResult<UserJwtResponse> login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BaseException(ErrorCode.USERNAME_OR_PASSWORD_INCORRECT);
        }

        String accessToken = jwtUtils.generateJwtToken(new JwtModel(user.getId(), user.getUsername(), user.getEmail(),jwtExpiration,
                user.getRoles().stream().map(r -> switchRole(r.getName())).collect(Collectors.toSet())));



        return new SuccessDataResult<>(SuccessCode.USER_SUCCESSFULLY_LOGGED_IN, new UserJwtResponse(user.getUsername(), accessToken, jwtExpiration));
    }

    private ERole switchRole(String role) {
        return switch (role.toUpperCase()) {
            case "ROLE_ADMIN" -> ERole.ROLE_ADMIN;
            case "ROLE_SUPER_ADMIN" -> ERole.ROLE_SUPER_ADMIN;
            case "USER" -> ERole.ROLE_USER;
            default -> throw new BaseException(ErrorCode.UNKNOWN_ROLE);
        };
    }
}
