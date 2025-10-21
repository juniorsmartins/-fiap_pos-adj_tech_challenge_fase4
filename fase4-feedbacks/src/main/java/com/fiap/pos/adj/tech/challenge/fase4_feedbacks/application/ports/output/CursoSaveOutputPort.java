package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.MessageCurso;

public interface CursoSaveOutputPort {

    void save(MessageCurso kafka);
}
