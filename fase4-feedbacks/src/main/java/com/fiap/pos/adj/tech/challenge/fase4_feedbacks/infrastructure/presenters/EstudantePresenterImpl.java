package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.EstudanteKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EstudantePresenterImpl implements EstudantePresenter {

    @Override
    public EstudanteEntity toEntity(EstudanteKafka kafka) {
        return new EstudanteEntity(kafka.id());
    }

    @Override
    public EstudanteEntity toEntity(Estudante model) {
        return new EstudanteEntity(model.id());
    }

    @Override
    public Estudante toModel(EstudanteEntity entity) {
        return new Estudante(entity.getId());
    }

    @Override
    public EstudanteResponse toResponse(Estudante model) {
        return new EstudanteResponse(model.id());
    }

    @Override
    public EstudanteResponse toResponse(EstudanteEntity entity) {
        return new EstudanteResponse(entity.getId());
    }
}
