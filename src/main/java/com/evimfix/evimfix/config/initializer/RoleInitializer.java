package com.evimfix.evimfix.config.initializer;

import com.evimfix.evimfix.dao.entites.concretes.user.Role;
import com.evimfix.evimfix.dao.model.enums.ERole;
import com.evimfix.evimfix.dao.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component("roleInitializer")
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.existsByName(ERole.ROLE_ADMIN.name())) {
            roleRepository.save(Role.builder().name(ERole.ROLE_ADMIN.name()).build());
        }
        if (roleRepository.existsByName(ERole.ROLE_USER.name())) {
            roleRepository.save(Role.builder().name(ERole.ROLE_USER.name()).build());
        }
        if (roleRepository.existsByName(ERole.ROLE_SUPER_ADMIN.name())) {
            roleRepository.save(Role.builder().name(ERole.ROLE_SUPER_ADMIN.name()).build());
        }
    }
}
