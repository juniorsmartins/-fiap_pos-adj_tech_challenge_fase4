package com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http409;

import java.io.Serial;

public final class NomeDuplicatedCustomException extends ResourceConflictRulesCustomException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NomeDuplicatedCustomException(final String nome) {
        super("exception.resource.conflict.rules.nome", nome);
    }
}
