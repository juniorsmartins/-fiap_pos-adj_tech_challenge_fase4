package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka.producer;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.kafka.PropertiesConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public final class ProducerImpl implements Producer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final PropertiesConfig PropertiesConfig;

    @Override
    public void sendEventCreateCursos(CursoKafka cursoKafka) {
        kafkaTemplate.send(PropertiesConfig.topicEventCreateCursos, UUID.randomUUID().toString(), cursoKafka);
        log.info("\n\n API-CURSOS sendEventCreateCursos - Mensagem enviada: {}. \n\n", cursoKafka);
    }
}
