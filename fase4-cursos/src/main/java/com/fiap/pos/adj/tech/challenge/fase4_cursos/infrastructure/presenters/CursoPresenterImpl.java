package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka.producer.MessageCurso;
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
        return new Curso(entity.getId(), entity.getNome(), entity.isAtivo());
    }

    @Override
    public CursoEntity toEntity(Curso curso) {
        return new CursoEntity(curso.getId(), curso.getNome(), curso.isAtivo());
    }

    @Override
    public MessageCurso toMessage(CursoResponse response) {
        return new MessageCurso(response.id(), response.nome());
    }
}
