package com.fiap.pos.adj.tech.challenge.fase4_users.utils;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.RoleEntity;

import java.util.UUID;

public final class RoleUtil {

    public static RoleEntity buildEntity(UUID id, RoleEnum roleEnum) {
        return new RoleEntity(id, roleEnum);
    }
}
