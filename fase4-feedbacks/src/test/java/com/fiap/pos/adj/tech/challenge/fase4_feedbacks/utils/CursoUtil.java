package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;

import java.util.UUID;

public final class CursoUtil {

    public static CursoEntity montarCursoEntity(UUID id) {
        return new CursoEntity(id);
    }
}
