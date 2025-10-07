package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response;

import java.util.UUID;

public record EstudanteResponse(
        UUID id,

        String nome,

        UsuarioResponse usuario
) {
}
