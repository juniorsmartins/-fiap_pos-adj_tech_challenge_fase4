package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.message.producer;

import java.util.UUID;

public record MessageCustomer(

        UUID id,

        String nome,

        String email
) {
}
