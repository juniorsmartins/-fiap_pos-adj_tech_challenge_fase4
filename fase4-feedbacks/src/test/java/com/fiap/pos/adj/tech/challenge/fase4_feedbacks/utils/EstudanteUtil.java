package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CustomerEntity;

import java.util.UUID;

public final class EstudanteUtil {

    public static CustomerEntity montarEstudanteEntity(UUID id) {
        return new CustomerEntity(id);
    }
}
