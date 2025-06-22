package com.evimfix.evimfix.dao.repository;

import com.evimfix.evimfix.dao.entites.concretes.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
}
