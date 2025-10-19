package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;

import java.util.UUID;

public interface CursoApagarOutputPort {

    void apagar(Curso curso);

    void apagarPorId(UUID id);
}
