package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.RoleEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.UserEntity;

import java.util.UUID;

public final class UsuarioUtil {

    public static UserEntity montarUserEntity(UUID id, String email, String password, RoleEntity roleEntity) {
        return new UserEntity(id, email, password, roleEntity);
    }
}
