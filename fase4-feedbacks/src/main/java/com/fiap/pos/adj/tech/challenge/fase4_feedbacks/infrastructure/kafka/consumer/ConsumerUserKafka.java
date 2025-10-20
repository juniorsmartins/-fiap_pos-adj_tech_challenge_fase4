package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteCriarInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public final class ConsumerUserKafka {

    private final EstudanteCriarInputPort estudanteCriarInputPort;

    @KafkaListener(topics = "${spring.kafka.topic.event-create-users}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "estudanteKafkaListenerContainerFactory")
    public void consumirEventoCreateUsers(final ConsumerRecord<String, CustomerKafka> kafka, Acknowledgment ack) {

        try {
            log.info("\n\n API-FEEDBACKS consumirEventoUsers - Mensagem recebida no tópico de eventos: {}. \n\n", kafka);
            ack.acknowledge(); // Confirmar o processamento da mensagem
            estudanteCriarInputPort.criar(kafka.value());

        } catch (Exception e) {
            log.error("\n\n API-FEEDBACKS consumirEventoUsers - Erro ao processar a mensagem: {}.\n\n", e.getMessage());
        }
    }
}
