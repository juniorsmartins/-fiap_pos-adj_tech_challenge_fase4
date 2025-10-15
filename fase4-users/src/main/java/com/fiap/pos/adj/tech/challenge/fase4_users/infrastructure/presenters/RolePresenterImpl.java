package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.RoleResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RolePresenterImpl implements RolePresenter {

    @Override
    public RoleResponse toResponse(Papel papel) {
        return new RoleResponse(papel.getId(), papel.getNome());
    }

    @Override
    public RoleResponse toResponse(RoleEntity entity) {
        return new RoleResponse(entity.getId(), entity.getNome());
    }

    @Override
    public RoleEntity toEntity(Papel papel) {
        return new RoleEntity(papel.getId(), papel.getNome());
    }

    @Override
    public Papel toPapel(RoleEntity entity) {
        return new Papel(entity.getId(), entity.getNome());
    }
}
