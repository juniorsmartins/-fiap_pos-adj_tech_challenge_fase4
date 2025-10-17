package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer;

import java.util.UUID;

public record EstudanteKafka(

        UUID id,

        String nome,

        String email
) {
}
