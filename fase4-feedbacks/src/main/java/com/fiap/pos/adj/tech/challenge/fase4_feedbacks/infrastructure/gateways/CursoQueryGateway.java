package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CursoQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.CursoPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
}
