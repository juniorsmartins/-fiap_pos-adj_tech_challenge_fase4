package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.consumer;

import java.util.UUID;

public record MessageCustomer(

        UUID id,

        String nome,

        String email
) {
}
