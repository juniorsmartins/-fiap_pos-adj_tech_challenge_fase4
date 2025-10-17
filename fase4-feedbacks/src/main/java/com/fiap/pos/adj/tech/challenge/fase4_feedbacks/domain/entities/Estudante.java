package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class Estudante {

    private final UUID id;

    private final String nome;

    private final String email;

    public Estudante(UUID id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}
