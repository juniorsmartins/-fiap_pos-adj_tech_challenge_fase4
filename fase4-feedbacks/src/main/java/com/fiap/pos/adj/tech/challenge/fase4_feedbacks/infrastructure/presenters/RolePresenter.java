package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.RoleResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.RoleEntity;

public interface RolePresenter {

    RoleResponse toResponse(Papel papel);

    RoleEntity toEntity(Papel papel);

    Papel toPapel(RoleEntity entity);
}
