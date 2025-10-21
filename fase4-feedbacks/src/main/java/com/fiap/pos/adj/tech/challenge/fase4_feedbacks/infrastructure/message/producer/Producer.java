package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.producer;

public interface Producer {

    void sendEventCreateFeedbacks(MessageFeedback kafka);
}
