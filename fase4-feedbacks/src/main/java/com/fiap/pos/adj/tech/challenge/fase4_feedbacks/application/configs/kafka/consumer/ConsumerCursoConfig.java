package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.kafka.consumer;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.MessageCurso;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class ConsumerCursoConfig {

    private final ConsumerBaseConfig consumerBaseConfig;

    @Bean
    public ConsumerFactory<String, MessageCurso> cursoConsumerFactory() {
        Map<String, Object> properties = consumerBaseConfig.consumerBaseConfigs();
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MessageCurso.class);
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageCurso> cursoKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageCurso> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cursoConsumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
