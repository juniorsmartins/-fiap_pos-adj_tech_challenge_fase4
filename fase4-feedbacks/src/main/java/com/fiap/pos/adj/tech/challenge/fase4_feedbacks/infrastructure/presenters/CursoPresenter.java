package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CursoKafka;

public interface CursoPresenter {

    CursoResponse toResponse(Curso curso);

    CursoResponse toResponse(CursoEntity entity);

    Curso toCurso(CursoEntity entity);

    CursoEntity toEntity(CursoKafka kafka);

    CursoEntity toEntity(Curso curso);
}
