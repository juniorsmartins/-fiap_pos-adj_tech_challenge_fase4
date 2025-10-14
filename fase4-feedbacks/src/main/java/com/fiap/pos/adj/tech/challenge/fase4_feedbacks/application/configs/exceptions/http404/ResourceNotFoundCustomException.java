package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class ResourceNotFoundCustomException extends RuntimeException permits EstudanteNotFoundCustomException,
        RoleNotFoundCustomException, CursoNotFoundCustomException, FeedbackNotFoundCustomException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    private final String value;

    protected ResourceNotFoundCustomException(final String messageKey, final String value) {
        super(messageKey);
        this.messageKey = messageKey;
        this.value = value;
    }
}
