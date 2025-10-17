package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class kafkaPropertiesConfig {

    @Value("${spring.kafka.topic.event-create-cursos}")
    public String topicEventCreateCursos;
}
