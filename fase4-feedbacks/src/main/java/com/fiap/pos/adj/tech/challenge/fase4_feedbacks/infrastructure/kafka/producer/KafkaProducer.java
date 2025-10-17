package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.producer;

public interface KafkaProducer {

    void enviarEventoFeedbacks(FeedbackKafka kafka);
}
