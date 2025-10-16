package com.fiap.pos.adj.tech.challenge.fase4_cursos.utils;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@EmbeddedKafka(partitions = 1,
        topics = {"${spring.kafka.topic.event-create-cursos}"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:0",
        "auto.create.topics.enable=true"})
@DirtiesContext
public abstract class BaseIntegrationTest {

}
