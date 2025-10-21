package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.CursoCriarInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public final class ConsumerCurso {

    private final CursoCriarInputPort cursoCriarInputPort;

    @KafkaListener(topics = "${spring.kafka.topic.event-create-cursos}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "cursoKafkaListenerContainerFactory")
    public void consumirEventoCreateCursos(final ConsumerRecord<String, MessageCurso> kafka, Acknowledgment ack) {

        try {
            log.info("\n\n API-FEEDBACKS consumirEventoCreateCursos - Mensagem recebida no tópico de eventos: {}. \n\n", kafka);
            ack.acknowledge(); // Confirmar o processamento da mensagem
            cursoCriarInputPort.criar(kafka.value());

        } catch (Exception e) {
            log.error("\n\n API-FEEDBACKS consumirEventoCreateCursos - Erro ao processar a mensagem: {}.\n\n", e.getMessage());
        }
    }
}
