package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;

import java.util.Optional;
import java.util.UUID;

public interface CursoQueryOutputPort {

    Optional<Curso> findByNome(String nome);

    Optional<Curso> findByIdAndAtivoTrue(UUID id);

    Optional<Curso> findById(UUID id);
}
