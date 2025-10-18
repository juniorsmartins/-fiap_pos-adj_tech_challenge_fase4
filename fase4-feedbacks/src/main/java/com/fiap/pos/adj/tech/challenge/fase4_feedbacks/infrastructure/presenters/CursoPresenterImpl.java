package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CursoKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CursoPresenterImpl implements CursoPresenter {

    @Override
    public CursoResponse toResponse(Curso curso) {
        return new CursoResponse(curso.id());
    }

    @Override
    public CursoResponse toResponse(CursoEntity entity) {
        return new CursoResponse(entity.getId());
    }

    @Override
    public Curso toCurso(CursoEntity entity) {
        return new Curso(entity.getId());
    }

    @Override
    public CursoEntity toEntity(CursoKafka kafka) {
        return new CursoEntity(kafka.id());
    }

    @Override
    public CursoEntity toEntity(Curso curso) {
        return new CursoEntity(curso.id());
    }
}
