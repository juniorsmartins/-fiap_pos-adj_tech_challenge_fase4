package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.producer;

public interface Producer {

    void sendEventCreateFeedbacks(MessageFeedback kafka);
}
