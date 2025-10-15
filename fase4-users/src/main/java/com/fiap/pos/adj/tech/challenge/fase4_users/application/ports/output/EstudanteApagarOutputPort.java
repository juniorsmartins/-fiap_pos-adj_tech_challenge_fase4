package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;

import java.util.UUID;

public interface EstudanteApagarOutputPort {

    void apagar(Estudante estudante);

    void apagarPorId(UUID id);
}
