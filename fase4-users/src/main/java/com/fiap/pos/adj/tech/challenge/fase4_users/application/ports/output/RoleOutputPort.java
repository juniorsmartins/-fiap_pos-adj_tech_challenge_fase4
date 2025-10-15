package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;

import java.util.Optional;

public interface RoleOutputPort {

    Optional<Papel> findByNome(RoleEnum nome);

    Papel save(Papel papel);
}
