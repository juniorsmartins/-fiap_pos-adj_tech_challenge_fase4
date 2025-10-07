package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.RoleResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Papel;

public interface RolePresenter {

    RoleResponse toRoleResponse(Papel papel);
}
