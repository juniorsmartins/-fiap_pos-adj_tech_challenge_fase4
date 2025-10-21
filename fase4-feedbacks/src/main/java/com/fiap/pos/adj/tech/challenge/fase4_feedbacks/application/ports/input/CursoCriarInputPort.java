package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.consumer.MessageCurso;

public interface CursoCriarInputPort {

    void criar(MessageCurso kafka);
}
