package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka.producer;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.kafka.kafkaPropertiesConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public final class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final kafkaPropertiesConfig kafkaPropertiesConfig;

    @Override
    public void sendEventCreateCursos(CursoKafka cursoKafka) {
        kafkaTemplate.send(kafkaPropertiesConfig.topicEventCreateCursos, UUID.randomUUID().toString(), cursoKafka);
        log.info("\n\n API-CURSOS sendEventCreateCursos - Mensagem enviada ao tópico: {}. \n\n", cursoKafka);
    }
}
