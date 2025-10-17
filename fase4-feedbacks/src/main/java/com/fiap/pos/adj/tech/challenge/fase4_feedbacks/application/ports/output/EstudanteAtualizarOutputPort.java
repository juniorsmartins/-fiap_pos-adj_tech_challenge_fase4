package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.EstudanteKafka;

public interface EstudanteAtualizarOutputPort {

    void atualizar(EstudanteKafka kafka);
}
