package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.RoleResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Papel;
import org.springframework.stereotype.Component;

@Component
public class RolePresenterImpl implements RolePresenter {

    @Override
    public RoleResponse toRoleResponse(Papel papel) {
        return new RoleResponse(papel.getId(), papel.getNome());
    }
}
