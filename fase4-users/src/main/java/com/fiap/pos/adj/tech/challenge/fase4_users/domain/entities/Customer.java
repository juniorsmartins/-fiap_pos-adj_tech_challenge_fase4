package com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http400.FieldWithNullEmptyOrBlankValueIsProhibitedException;
import lombok.Getter;

import java.util.UUID;

@Getter
public final class Customer {

    private final UUID id;

    private final String nome;

    private final boolean ativo;

    private final Usuario user;

    public Customer(UUID id, String nome, boolean ativo, Usuario user) {
        checkNotBlank("nome", nome);
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.user = user;
    }

    private void checkNotBlank(String fieldName, String value) {
        if (value == null || value.isBlank()) {
            throw new FieldWithNullEmptyOrBlankValueIsProhibitedException(fieldName);
        }
    }
}
