package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka.producer.CursoKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CursoPresenterImpl implements CursoPresenter {

    @Override
    public CursoResponse toResponse(Curso curso) {
        return new CursoResponse(curso.getId(), curso.getNome());
    }

    @Override
    public CursoResponse toResponse(CursoEntity entity) {
        return new CursoResponse(entity.getId(), entity.getNome());
    }

    @Override
    public Curso toCurso(CursoEntity entity) {
        return new Curso(entity.getId(), entity.getNome());
    }

    @Override
    public CursoEntity toEntity(Curso curso) {
        return new CursoEntity(curso.getId(), curso.getNome());
    }

    @Override
    public CursoKafka toKafka(CursoResponse response) {
        return new CursoKafka(response.id(), response.nome());
    }
}
