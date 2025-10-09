package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CursoSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.CursoPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CursoRepository;
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
