package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoApagarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.presenters.CursoPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CursoApagarGateway implements CursoApagarOutputPort {

    private final CursoRepository cursoRepository;

    private final CursoPresenter cursoPresenter;

    @Transactional
    @Override
    public void apagar(Curso curso) {
        var entity = cursoPresenter.toEntity(curso);
        cursoRepository.delete(entity);
    }

    @Transactional
    @Override
    public void apagarPorId(UUID id) {
        cursoRepository.deleteById(id);
    }
}
