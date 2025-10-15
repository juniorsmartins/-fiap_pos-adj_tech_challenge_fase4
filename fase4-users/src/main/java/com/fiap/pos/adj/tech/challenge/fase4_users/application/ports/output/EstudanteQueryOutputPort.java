package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;

import java.util.Optional;
import java.util.UUID;

public interface EstudanteQueryOutputPort {

    Optional<Estudante> findByEmail(String email);

    Optional<Estudante> findById(UUID id);
}
