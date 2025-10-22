package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        topics = {"${spring.kafka.topic.event-create-feedbacks}",
                "${spring.kafka.topic.event-create-users}",
                "${spring.kafka.topic.event-create-cursos}"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:0",
        "auto.create.topics.enable=true"})
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@DirtiesContext
public abstract class BaseIntegrationTest {

}
