package com.evimfix.evimfix.config.initializer;

import com.evimfix.evimfix.dao.entites.concretes.user.Role;
import com.evimfix.evimfix.dao.entites.concretes.user.User;
import com.evimfix.evimfix.dao.repository.RoleRepository;
import com.evimfix.evimfix.dao.repository.UserRepository;
import com.evimfix.evimfix.exception.model.BaseException;
import com.evimfix.evimfix.exception.model.codes.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@DependsOn("roleInitializer")
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${app.admin.first-name}")
    private String firstName;

    @Value("${app.admin.last-name}")
    private String lastName;

    @Value("${app.admin.username}")
    private String username;

    @Value("${app.admin.email}")
    private String email;

    @Value("${app.admin.password}")
    private String password;

    @Override
    public void run(String... args) {

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new BaseException(ErrorCode.ROLE_NOT_FOUND));

        Role superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN")
                .orElseThrow(() -> new BaseException(ErrorCode.ROLE_NOT_FOUND));

        List<Role> roles = new ArrayList<>();
        roles.add(adminRole);
        roles.add(superAdminRole);

        if (!userRepository.existsByUsername(username)) {
            User user = User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .username(username)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .roles(roles).build();

            userRepository.save(user);
        }
    }
}
