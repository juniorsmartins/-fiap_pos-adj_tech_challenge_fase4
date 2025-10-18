package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CursoKafka;

public interface CursoSaveOutputPort {

    void save(CursoKafka kafka);
}
