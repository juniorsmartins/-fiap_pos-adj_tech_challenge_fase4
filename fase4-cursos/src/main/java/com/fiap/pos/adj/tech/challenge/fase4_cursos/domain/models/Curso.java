package com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http400.FieldWithNullEmptyOrBlankValueIsProhibitedException;
import lombok.Getter;

import java.util.UUID;

@Getter
public final class Curso {

    private final UUID id;

    private final String nome;

    private final boolean ativo;

    public Curso(UUID id, String nome, boolean ativo) {
        checkNotBlank("nome", nome);
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    private void checkNotBlank(String fieldName, String value) {
        if (value == null || value.isBlank()) {
            throw new FieldWithNullEmptyOrBlankValueIsProhibitedException(fieldName);
        }
    }
}
