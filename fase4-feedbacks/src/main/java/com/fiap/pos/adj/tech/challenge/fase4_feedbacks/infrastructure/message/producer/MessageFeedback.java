package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.producer;

import java.util.UUID;

public record MessageFeedback(

        UUID id,

        int nota,

        String comentario,

        UUID curso,

        UUID estudante
) {
}
