package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404;

import java.io.Serial;
import java.util.UUID;

public final class CursoNotFoundCustomException extends ResourceNotFoundCustomException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CursoNotFoundCustomException(final UUID id) {
        super("exception.resource.not-found.curso", id.toString());
    }
}
