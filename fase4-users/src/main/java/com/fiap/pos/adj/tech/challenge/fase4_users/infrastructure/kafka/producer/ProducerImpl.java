package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.kafka.PropertiesConfig;
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
    public void sendEventCreateCustomers(MessageCustomer messageCustomer) {
        kafkaTemplate.send(PropertiesConfig.topicEventCreateUsers, UUID.randomUUID().toString(), messageCustomer);
        log.info("\n\n API-USERS sendEventCreateCustomers - Mensagem enviada: {}. \n\n", messageCustomer);
    }
}
