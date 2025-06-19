package com.evimfix.evimfix.dao.repository;

import com.evimfix.evimfix.dao.entites.concretes.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
