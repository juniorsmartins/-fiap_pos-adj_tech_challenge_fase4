package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CursoKafka;

public interface CursoCriarInputPort {

    void criar(CursoKafka kafka);
}
