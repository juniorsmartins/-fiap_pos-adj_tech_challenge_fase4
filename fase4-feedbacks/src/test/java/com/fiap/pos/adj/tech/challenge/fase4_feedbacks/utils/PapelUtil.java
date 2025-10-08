package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.RoleEntity;

import java.util.UUID;

public final class PapelUtil {

    public static RoleEntity montarRoleEntity(UUID id, RoleEnum roleEnum) {
        return new RoleEntity(id, roleEnum);
    }
}
