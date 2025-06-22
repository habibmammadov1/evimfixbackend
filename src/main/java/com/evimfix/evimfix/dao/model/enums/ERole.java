package com.evimfix.evimfix.dao.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERole {
    ROLE_USER(1L),
    ROLE_ADMIN(2L),
    ROLE_SUPER_ADMIN(3L);

    private final Long id;
}
