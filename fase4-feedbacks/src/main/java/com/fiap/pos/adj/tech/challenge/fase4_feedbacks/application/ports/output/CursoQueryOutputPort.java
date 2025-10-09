package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;

import java.util.Optional;

public interface CursoQueryOutputPort {

    Optional<Curso> findByNome(String nome);
}
