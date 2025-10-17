package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteAtualizarInputPort;
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
public final class KafkaConsumerUsers {

    private final EstudanteCriarInputPort estudanteCriarInputPort;

    private final EstudanteAtualizarInputPort estudanteAtualizarInputPort;

    @KafkaListener(topics = "${spring.kafka.topic.event-create-users}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumirEventoCreateUsers(final ConsumerRecord<String, EstudanteKafka> kafka, Acknowledgment ack) {

        try {
            log.info("\n\n API-FEEDBACKS consumirEventoUsers - Mensagem recebida no tópico de eventos: {}. \n\n", kafka);
            ack.acknowledge(); // Confirmar o processamento da mensagem
            estudanteCriarInputPort.criar(kafka.value());

        } catch (Exception e) {
            log.error("\n\n API-FEEDBACKS consumirEventoUsers - Erro ao processar a mensagem: {}.\n\n", e.getMessage());
        }
    }

    @KafkaListener(topics = "${spring.kafka.topic.event-update-users}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumirEventoUpdateUsers(final ConsumerRecord<String, EstudanteKafka> kafka, Acknowledgment ack) {

        try {
            log.info("\n\n API-FEEDBACKS consumirEventoUpdateUsers - Mensagem recebida no tópico de eventos: {}. \n\n", kafka);
            ack.acknowledge(); // Confirmar o processamento da mensagem
            estudanteAtualizarInputPort.atualizar(kafka.value());

        } catch (Exception e) {
            log.error("\n\n API-FEEDBACKS consumirEventoUpdateUsers - Erro ao processar a mensagem: {}.\n\n", e.getMessage());
        }
    }
}
