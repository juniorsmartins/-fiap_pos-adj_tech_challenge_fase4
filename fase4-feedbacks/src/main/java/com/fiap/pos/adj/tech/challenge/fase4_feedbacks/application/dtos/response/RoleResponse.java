package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.enums.RoleEnum;

import java.util.UUID;

public record RoleResponse(
        UUID id,

        RoleEnum nome
) {
}
