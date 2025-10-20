package com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations;

import java.util.UUID;

public interface CustomerValidation {

    void checkDuplicateEmail(UUID customerId, String email);
}
