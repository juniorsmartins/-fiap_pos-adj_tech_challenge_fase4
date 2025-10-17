package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer;

public interface KafkaProducer {

    void enviarEventoUsers(EstudanteKafka kafka);
}
