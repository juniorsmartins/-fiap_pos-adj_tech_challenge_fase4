package com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http404;

import java.io.Serial;
import java.util.UUID;

public final class CustomerNotFoundCustomException extends ResourceNotFoundCustomException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundCustomException(final UUID customerId) {
        super("exception.resource.not-found.customer", customerId.toString());
    }
}
