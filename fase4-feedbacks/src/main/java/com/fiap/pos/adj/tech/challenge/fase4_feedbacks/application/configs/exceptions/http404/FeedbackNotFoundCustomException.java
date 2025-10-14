package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404;

import java.io.Serial;
import java.util.UUID;

public final class FeedbackNotFoundCustomException extends ResourceNotFoundCustomException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FeedbackNotFoundCustomException(final UUID id) {
        super("exception.resource.not-found.feedback", id.toString());
    }
}
