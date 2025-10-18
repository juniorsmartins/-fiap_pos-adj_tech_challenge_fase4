package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.producer;

import java.util.UUID;

public record FeedbackKafka(

        UUID id,

        int nota,

        String comentario,

        UUID curso,

        UUID estudante
) {
}
