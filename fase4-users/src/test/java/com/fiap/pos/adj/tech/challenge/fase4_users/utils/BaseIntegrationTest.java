package com.fiap.pos.adj.tech.challenge.fase4_users.utils;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@EmbeddedKafka(partitions = 1,
        topics = {"${spring.kafka.topic.event-create-users}",
                "${spring.kafka.topic.event-update-users}"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:0",
        "auto.create.topics.enable=true"})
@DirtiesContext
public abstract class BaseIntegrationTest {

}
