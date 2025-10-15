package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.RoleResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.RoleEntity;

public interface RolePresenter {

    RoleResponse toResponse(Papel papel);

    RoleResponse toResponse(RoleEntity entity);

    RoleEntity toEntity(Papel papel);

    Papel toPapel(RoleEntity entity);
}
