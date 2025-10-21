package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class Feedback {

    private final UUID id;

    private final int nota;

    private final String comentario;

    private final Curso curso;

    private final Customer customer;

    public Feedback(UUID id, int nota, String comentario, Curso curso, Customer customer) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.curso = curso;
        this.customer = customer;
    }
}
