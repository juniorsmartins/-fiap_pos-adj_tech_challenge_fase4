package com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class Estudante {

    private final UUID id;

    private final String nome;

    private final Usuario user;

    public Estudante(UUID id, String nome, Usuario user) {
        this.id = id;
        this.nome = nome;
        this.user = user;
    }
}
