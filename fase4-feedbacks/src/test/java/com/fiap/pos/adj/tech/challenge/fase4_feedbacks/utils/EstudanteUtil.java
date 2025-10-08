package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.EstudanteRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.UserEntity;

import java.util.UUID;

public final class EstudanteUtil {

    public static EstudanteRequest montarEstudanteRequest(String nome, String email, String password) {
        return new EstudanteRequest(nome, email, password);
    }

    public static EstudanteEntity montarEstudanteEntity(UUID id, String nome, UserEntity userEntity) {
        return new EstudanteEntity(id, nome, userEntity);
    }
}
