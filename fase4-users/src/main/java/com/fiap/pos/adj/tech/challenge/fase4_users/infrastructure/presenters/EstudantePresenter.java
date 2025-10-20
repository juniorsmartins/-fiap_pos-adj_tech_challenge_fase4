package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer.EstudanteKafka;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.CustomerEntity;

public interface EstudantePresenter {

    CustomerResponse toResponse(Estudante estudante);

    CustomerResponse toResponse(CustomerEntity entity);

    CustomerEntity toEntity(Estudante estudante);

    Estudante toEstudante(CustomerEntity entity);

    EstudanteKafka toKafka(CustomerResponse response);
}
