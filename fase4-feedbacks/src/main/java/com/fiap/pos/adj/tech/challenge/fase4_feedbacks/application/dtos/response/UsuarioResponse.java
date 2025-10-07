package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,

        String email,

        String password,

        RoleResponse role
) {
}
