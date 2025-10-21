package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FeedbackRequest(

        @NotNull
        @Min(1)
        @Max(5)
        int nota,

        String comentario,

        @NotNull
        UUID curso,

        @NotNull
        UUID customer
) {
}
