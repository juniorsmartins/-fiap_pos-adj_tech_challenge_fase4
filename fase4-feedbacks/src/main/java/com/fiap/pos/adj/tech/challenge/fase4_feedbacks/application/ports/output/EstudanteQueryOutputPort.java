package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;

import java.util.Optional;

public interface EstudanteQueryOutputPort {

    Optional<Estudante> findByEmail(String email);
}
