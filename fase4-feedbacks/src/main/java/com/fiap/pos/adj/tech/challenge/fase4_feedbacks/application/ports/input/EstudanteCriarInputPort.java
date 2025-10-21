package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.MessageCustomer;

public interface EstudanteCriarInputPort {

    void criar(MessageCustomer kafka);
}
