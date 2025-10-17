package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer;

import java.util.UUID;

public record EstudanteKafka(

        UUID id,

        String nome,

        String email
) {
}
