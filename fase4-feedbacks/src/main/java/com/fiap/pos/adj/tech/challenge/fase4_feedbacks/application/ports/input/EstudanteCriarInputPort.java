package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CustomerKafka;

public interface EstudanteCriarInputPort {

    void criar(CustomerKafka kafka);
}
