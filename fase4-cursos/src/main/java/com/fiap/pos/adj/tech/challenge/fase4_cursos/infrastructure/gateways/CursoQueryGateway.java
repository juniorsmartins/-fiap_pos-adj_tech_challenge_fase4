package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.presenters.CursoPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CursoQueryGateway implements CursoQueryOutputPort {

    private final CursoRepository cursoRepository;

    private final CursoPresenter cursoPresenter;

    @Transactional(readOnly = true)
    @Override
    public Optional<Curso> findByNome(String nome) {
        return cursoRepository.findByNome(nome)
                .map(cursoPresenter::toCurso);
    }

    @Override
    public Optional<Curso> findByIdAndAtivoTrue(UUID id) {
        return cursoRepository.findByIdAndAtivoTrue(id)
                .map(cursoPresenter::toCurso);
    }
}
