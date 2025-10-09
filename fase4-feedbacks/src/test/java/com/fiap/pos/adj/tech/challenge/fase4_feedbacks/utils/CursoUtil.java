package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;

import java.util.UUID;

public final class CursoUtil {

    public static CursoRequest montarCursoRequest(String nome) {
        return new CursoRequest(nome);
    }

    public static CursoEntity montarCursoEntity(UUID id, String nome) {
        return new CursoEntity(id, nome);
    }
}
