package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class Usuario {

    private final UUID id;

    private final String email;

    private final String password;

    private final Papel role;

    public Usuario(UUID id, String email, String password, Papel role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
