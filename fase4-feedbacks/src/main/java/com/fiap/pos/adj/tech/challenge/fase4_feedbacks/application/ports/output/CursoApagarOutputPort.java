package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;

import java.util.UUID;

public interface CursoApagarOutputPort {

    void apagar(Curso curso);

    void apagarPorId(UUID id);
}
