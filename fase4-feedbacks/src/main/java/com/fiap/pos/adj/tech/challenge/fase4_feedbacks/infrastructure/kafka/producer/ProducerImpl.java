package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.producer;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.kafka.PropertiesConfig;
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
    public void enviarEventoFeedbacks(FeedbackKafka kafka) {
        kafkaTemplate.send(PropertiesConfig.topicEventCreateFeedbacks, UUID.randomUUID().toString(), kafka);
        log.info("\n\n API-FEEDBACKS enviarEventoFeedbacks - Mensagem enviada ao tópico de eventos: {}. \n\n", kafka);
    }
}
