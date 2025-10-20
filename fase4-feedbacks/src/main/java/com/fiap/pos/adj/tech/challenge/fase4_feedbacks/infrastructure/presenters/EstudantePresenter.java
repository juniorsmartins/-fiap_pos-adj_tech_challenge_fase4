package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CustomerKafka;

public interface EstudantePresenter {

    EstudanteEntity toEntity(CustomerKafka kafka);

    EstudanteEntity toEntity(Estudante model);

    Estudante toModel(EstudanteEntity entity);

    EstudanteResponse toResponse(Estudante model);

    EstudanteResponse toResponse(EstudanteEntity entity);
}
