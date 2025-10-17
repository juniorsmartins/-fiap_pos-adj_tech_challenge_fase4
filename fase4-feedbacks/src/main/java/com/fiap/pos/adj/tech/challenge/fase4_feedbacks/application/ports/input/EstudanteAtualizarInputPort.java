package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.EstudanteKafka;

public interface EstudanteAtualizarInputPort {

    void atualizar(EstudanteKafka kafka);
}
