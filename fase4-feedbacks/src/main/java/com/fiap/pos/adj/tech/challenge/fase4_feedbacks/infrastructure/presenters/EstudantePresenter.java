package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CustomerEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CustomerKafka;

public interface EstudantePresenter {

    CustomerEntity toEntity(CustomerKafka kafka);

    CustomerEntity toEntity(Estudante model);

    Estudante toModel(CustomerEntity entity);

    EstudanteResponse toResponse(Estudante model);

    EstudanteResponse toResponse(CustomerEntity entity);
}
