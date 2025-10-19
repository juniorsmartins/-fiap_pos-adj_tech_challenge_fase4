package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka.producer.CursoKafka;

public interface CursoPresenter {

    CursoResponse toResponse(Curso curso);

    CursoResponse toResponse(CursoEntity entity);

    Curso toCurso(CursoEntity entity);

    CursoEntity toEntity(Curso curso);

    CursoKafka toKafka(CursoResponse response);
}
