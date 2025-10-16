package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.consumer;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.EstudanteKafka;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.event-create-users}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumirEventoUsers(final ConsumerRecord<String, EstudanteKafka> estudanteKafka, Acknowledgment ack) {

        try {
            log.info("\n\n consumirEventoUser - Mensagem recebida no tópico de eventos: {}. \n\n", estudanteKafka);
            ack.acknowledge(); // Confirmar o processamento da mensagem

        } catch (Exception e) {
            log.error("\n\n consumirEventoUser - Erro ao processar a mensagem: {}.\n\n", e.getMessage());
        }
    }
}
