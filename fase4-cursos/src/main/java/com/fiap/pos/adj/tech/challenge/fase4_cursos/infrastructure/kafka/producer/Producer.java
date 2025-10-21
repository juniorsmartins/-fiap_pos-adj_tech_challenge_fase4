package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka.producer;

public interface Producer {

    void sendEventCreateCursos(MessageCurso messageCurso);
}
