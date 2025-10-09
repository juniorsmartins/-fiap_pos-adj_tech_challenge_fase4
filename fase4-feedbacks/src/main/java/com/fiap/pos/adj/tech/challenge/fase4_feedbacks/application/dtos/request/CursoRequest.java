package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CursoRequest(

        @NotBlank
        String nome
) {
}
