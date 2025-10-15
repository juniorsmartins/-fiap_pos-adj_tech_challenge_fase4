package com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class kafkaPropertiesConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    public String consumerGroupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    public String consumerAutoOffsetReset;

    @Value("${spring.kafka.topic.event-create-users}")
    public String topicEventCreateUsers;
}
