package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.consumer;

import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer.EstudanteKafka;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class ConsumerUserKafka {

    @KafkaListener(topics = "${spring.kafka.topic.event-create-users}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "estudanteKafkaListenerContainerFactory")
    public void consumirEventoUsers(final ConsumerRecord<String, EstudanteKafka> kafka, Acknowledgment ack) {

        try {
            log.info("\n\n API-USERS consumirEventoUsers - Mensagem recebida no tópico de eventos: {}. \n\n", kafka);
            ack.acknowledge(); // Confirmar o processamento da mensagem

        } catch (Exception e) {
            log.error("\n\n API-USERS consumirEventoUsers - Erro ao processar a mensagem: {}.\n\n", e.getMessage());
        }
    }
}
