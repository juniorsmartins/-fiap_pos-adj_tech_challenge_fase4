package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.kafka.kafkaPropertiesConfig;
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
    public void enviarEventoUsers(EstudanteKafka estudanteKafka) {
        kafkaTemplate.send(kafkaPropertiesConfig.topicEventCreateUsers, UUID.randomUUID().toString(), estudanteKafka);
        log.info("\n\n API-USERS enviarEventoUsers - Mensagem enviada ao tópico de eventos: {}. \n\n", estudanteKafka);
    }
}
