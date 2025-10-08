package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;

import java.util.Optional;
import java.util.UUID;

public interface EstudanteQueryOutputPort {

    Optional<Estudante> findByEmail(String email);

    Optional<Estudante> findById(UUID id);
}
