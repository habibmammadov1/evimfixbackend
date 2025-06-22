package com.evimfix.evimfix.services.concretes;

import com.evimfix.evimfix.config.security.jwt.JwtModel;
import com.evimfix.evimfix.config.security.services.JwtUtils;
import com.evimfix.evimfix.core.utilities.results.DataResult;
import com.evimfix.evimfix.core.utilities.results.Result;
import com.evimfix.evimfix.core.utilities.results.SuccessDataResult;
import com.evimfix.evimfix.core.utilities.results.SuccessResult;
import com.evimfix.evimfix.dao.entites.concretes.user.Role;
import com.evimfix.evimfix.dao.entites.concretes.user.User;
import com.evimfix.evimfix.dao.model.enums.ERole;
import com.evimfix.evimfix.dao.model.request.CreateAdminRequest;
import com.evimfix.evimfix.dao.model.request.LoginRequest;
import com.evimfix.evimfix.dao.model.response.UserJwtResponse;
import com.evimfix.evimfix.dao.repository.UserRepository;
import com.evimfix.evimfix.exception.model.BaseException;
import com.evimfix.evimfix.exception.model.codes.ErrorCode;
import com.evimfix.evimfix.exception.model.codes.SuccessCode;
import com.evimfix.evimfix.mapper.admin.AdminMapper;
import com.evimfix.evimfix.services.abstracts.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AdminMapper adminMapper;

    @Value("${action.app.jwtExpirationMs}")
    private Long jwtExpiration;


    @Override
    public DataResult<UserJwtResponse> adminLogin(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BaseException(ErrorCode.USERNAME_OR_PASSWORD_INCORRECT);
        }

        String accessToken = jwtUtils.generateJwtToken(new JwtModel(user.getId(), user.getUsername(), user.getEmail(),jwtExpiration,
                user.getRoles().stream().map(r -> switchRole(r.getName())).collect(Collectors.toSet())));
        return new SuccessDataResult<>(SuccessCode.ADMIN_SUCCESSFULLY_LOGGED_IN, new UserJwtResponse(user.getUsername(), accessToken, jwtExpiration));
    }

    private ERole switchRole(String role) {
        return switch (role.toUpperCase()) {
            case "ROLE_ADMIN" -> ERole.ROLE_ADMIN;
            case "ROLE_SUPER_ADMIN" -> ERole.ROLE_SUPER_ADMIN;
            case "USER" -> ERole.ROLE_USER;
            default -> throw new BaseException(ErrorCode.UNKNOWN_ROLE);
        };
    }

    @Override
    @Transactional
    public Result create(CreateAdminRequest createAdminRequest) {

        if (userRepository.existsByUsername(createAdminRequest.getUsername())) {
            throw new BaseException(ErrorCode.USER_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(createAdminRequest.getEmail())) {
            throw new BaseException(ErrorCode.USER_ALREADY_EXISTS);
        }

        User admin = adminMapper.createAdminRequestToUser(createAdminRequest);
        admin.setRoles(List.of(Role.builder().id(ERole.ROLE_ADMIN.getId()).build()));

        admin.setPassword(passwordEncoder.encode(createAdminRequest.getPassword()));
        userRepository.save(admin);
        return new SuccessResult(SuccessCode.ADMIN_SUCCESSFULLY_CREATED);
    }
}
