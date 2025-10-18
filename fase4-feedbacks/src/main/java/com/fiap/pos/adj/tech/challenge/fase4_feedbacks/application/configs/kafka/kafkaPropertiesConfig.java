package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class kafkaPropertiesConfig {

    @Value("${spring.kafka.topic.event-create-feedbacks}")
    public String topicEventCreateFeedbacks;

    @Value("${spring.kafka.topic.event-create-users}")
    public String topicEventCreateUsers;

    @Value("${spring.kafka.topic.event-create-cursos}")
    public String topicEventCreateCursos;
}
