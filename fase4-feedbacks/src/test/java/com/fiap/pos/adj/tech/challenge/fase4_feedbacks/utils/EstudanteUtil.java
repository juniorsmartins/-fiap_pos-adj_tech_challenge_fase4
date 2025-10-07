package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.EstudanteRequest;

public final class EstudanteUtil {

    public static EstudanteRequest montarEstudanteRequest(String nome, String email, String password) {
        return new EstudanteRequest(nome, email, password);
    }
}
