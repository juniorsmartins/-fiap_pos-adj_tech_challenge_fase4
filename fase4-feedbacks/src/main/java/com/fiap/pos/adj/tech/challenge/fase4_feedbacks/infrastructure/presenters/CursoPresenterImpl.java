package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;
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
}
