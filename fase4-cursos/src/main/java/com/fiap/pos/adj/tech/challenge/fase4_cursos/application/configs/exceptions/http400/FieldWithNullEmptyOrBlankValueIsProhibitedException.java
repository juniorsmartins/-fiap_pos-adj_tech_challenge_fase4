package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http400;

import java.io.Serial;

public final class FieldWithNullEmptyOrBlankValueIsProhibitedException extends BadRequestCustomException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FieldWithNullEmptyOrBlankValueIsProhibitedException(final String fieldName) {
        super("exception.resource.bad-request.field-null-empty-blank", fieldName);
    }
}
