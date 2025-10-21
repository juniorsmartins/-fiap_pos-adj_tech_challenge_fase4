package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.consumer.MessageCustomer;

public interface CustomerCriarInputPort {

    void criar(MessageCustomer kafka);
}
