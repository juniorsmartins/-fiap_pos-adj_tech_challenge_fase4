package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;

public interface EstudantePresenter {

    EstudanteResponse toResponse(Estudante estudante);

    EstudanteResponse toResponse(EstudanteEntity entity);

    EstudanteEntity toEntity(Estudante estudante);

    Estudante toEstudante(EstudanteEntity entity);
}
