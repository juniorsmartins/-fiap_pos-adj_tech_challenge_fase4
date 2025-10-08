package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.validations;

import java.util.UUID;

public interface EstudanteValidation {

    void checkDuplicateEmail(UUID customerId, String email);
}
