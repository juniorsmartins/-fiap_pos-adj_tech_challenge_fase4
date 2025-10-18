package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;

import java.util.UUID;

public final class EstudanteUtil {

    public static EstudanteEntity montarEstudanteEntity(UUID id) {
        return new EstudanteEntity(id);
    }
}
