package com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class Curso {

    private final UUID id;

    private final String nome;

    public Curso(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
