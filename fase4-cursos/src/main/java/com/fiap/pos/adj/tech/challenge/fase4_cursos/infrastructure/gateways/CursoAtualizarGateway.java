package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.presenters.CursoPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CursoAtualizarGateway implements CursoAtualizarOutputPort {

    private final CursoRepository cursoRepository;

    private final CursoPresenter cursoPresenter;

    @Transactional
    @Override
    public CursoResponse atualizar(Curso curso) {

        return cursoRepository.findByIdAndAtivoTrue(curso.getId())
                .map(entity -> {
                    entity.setNome(curso.getNome());
                    return entity;
                })
                .map(cursoRepository::saveAndFlush)
                .map(cursoPresenter::toResponse)
                .orElseThrow(() -> new CursoNotFoundCustomException(curso.getId()));
    }
}
