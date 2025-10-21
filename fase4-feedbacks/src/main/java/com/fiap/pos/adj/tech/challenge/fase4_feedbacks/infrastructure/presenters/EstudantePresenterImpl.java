package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CustomerEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CustomerKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EstudantePresenterImpl implements EstudantePresenter {

    @Override
    public CustomerEntity toEntity(CustomerKafka kafka) {
        return new CustomerEntity(kafka.id());
    }

    @Override
    public CustomerEntity toEntity(Estudante model) {
        return new CustomerEntity(model.id());
    }

    @Override
    public Estudante toModel(CustomerEntity entity) {
        return new Estudante(entity.getId());
    }

    @Override
    public EstudanteResponse toResponse(Estudante model) {
        return new EstudanteResponse(model.id());
    }

    @Override
    public EstudanteResponse toResponse(CustomerEntity entity) {
        return new EstudanteResponse(entity.getId());
    }
}
