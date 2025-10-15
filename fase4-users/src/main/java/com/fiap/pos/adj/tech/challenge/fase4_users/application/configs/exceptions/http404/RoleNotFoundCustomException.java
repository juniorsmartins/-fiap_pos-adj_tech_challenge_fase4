package com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http404;

import java.io.Serial;

public final class RoleNotFoundCustomException extends ResourceNotFoundCustomException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RoleNotFoundCustomException(final String roleName) {
        super("exception.resource.not-found.role", roleName);
    }
}
