package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.presenters.CursoPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CursoSaveGateway implements CursoSaveOutputPort {

    private final CursoRepository cursoRepository;

    private final CursoPresenter cursoPresenter;

    @Transactional
    @Override
    public CursoResponse save(Curso curso) {

        return Optional.ofNullable(curso)
                .map(cursoPresenter::toEntity)
                .map(cursoRepository::save)
                .map(cursoPresenter::toResponse)
                .orElseThrow();
    }
}
