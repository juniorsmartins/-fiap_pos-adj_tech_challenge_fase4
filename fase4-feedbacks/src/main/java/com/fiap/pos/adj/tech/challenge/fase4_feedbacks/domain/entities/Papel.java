package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.enums.RoleEnum;
import lombok.Getter;

import java.util.UUID;

@Getter
public final class Papel {

    private final UUID id;

    private final RoleEnum nome;

    public Papel(UUID id, RoleEnum nome) {
        this.id = id;
        this.nome = nome;
    }
}
