package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response;

import java.util.UUID;

public record FeedbackResponse(

        UUID id,

        int nota,

        String comentario,

        CursoResponse curso,

        CustomerResponse customer
) {
}
