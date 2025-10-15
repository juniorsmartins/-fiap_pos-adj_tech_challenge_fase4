package com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_ESTUDANTE("ROLE_ESTUDANTE");

    private final String value;
}
