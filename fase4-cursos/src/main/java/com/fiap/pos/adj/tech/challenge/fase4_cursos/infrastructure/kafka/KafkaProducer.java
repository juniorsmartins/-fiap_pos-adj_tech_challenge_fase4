package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka;

public interface KafkaProducer {

    void enviarEventoCursos(CursoKafka cursoKafka);
}
