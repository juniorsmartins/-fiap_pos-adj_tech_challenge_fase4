package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka.producer;

import java.util.UUID;

public record MessageCurso(

        UUID id,

        String nome
) {
}
