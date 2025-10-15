package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.EstudanteEntity;

public interface EstudantePresenter {

    EstudanteResponse toResponse(Estudante estudante);

    EstudanteResponse toResponse(EstudanteEntity entity);

    EstudanteEntity toEntity(Estudante estudante);

    Estudante toEstudante(EstudanteEntity entity);
}
