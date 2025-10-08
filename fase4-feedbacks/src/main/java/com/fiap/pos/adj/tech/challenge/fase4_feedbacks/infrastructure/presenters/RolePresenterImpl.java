package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.RoleResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RolePresenterImpl implements RolePresenter {

    @Override
    public RoleResponse toResponse(Papel papel) {
        return new RoleResponse(papel.getId(), papel.getNome());
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
