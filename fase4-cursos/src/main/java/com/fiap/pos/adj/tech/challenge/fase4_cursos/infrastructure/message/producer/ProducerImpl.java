package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.message.producer;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.message.PropertiesConfig;
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
    public void sendEventCreateCursos(MessageCurso messageCurso) {
        kafkaTemplate.send(PropertiesConfig.topicEventCreateCursos, UUID.randomUUID().toString(), messageCurso);
        log.info("\n\n API-CURSOS sendEventCreateCursos - Mensagem enviada: {}. \n\n", messageCurso);
    }
}
