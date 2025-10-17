package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.EstudanteKafka;

public interface KafkaProducer {

    void enviarEventoUsers(EstudanteKafka kafka);
}
