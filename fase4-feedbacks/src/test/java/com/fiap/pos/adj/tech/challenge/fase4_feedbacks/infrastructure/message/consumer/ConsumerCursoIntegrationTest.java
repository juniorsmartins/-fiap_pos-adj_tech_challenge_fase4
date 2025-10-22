package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.consumer;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CursoRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.BaseIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsumerCursoIntegrationTest extends BaseIntegrationTest {

    @Value("${spring.kafka.topic.event-create-cursos}")
    private String topicCreateCursos;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CursoRepository cursoRepository;

    @AfterEach
    void tearDown() {
        cursoRepository.deleteAll();
    }

    @Nested
    @DisplayName("ConsumerCursoValid")
    class ConsumerCursoValid {

        @Test
        void dadaMensagemValida_quandoAcionadoConsumerCurso_entaoSalvarNoBancoDeDadosComSucesso() throws InterruptedException {
            var idCurso = UUID.randomUUID();
            var message = new MessageCurso(idCurso, "DevOps - CI e CD");
            var key = UUID.randomUUID().toString();

            assertEquals(0, cursoRepository.count(), "Deveria haver zero cursos no banco");

            kafkaTemplate.send(topicCreateCursos, key, message);
            Thread.sleep(2000);

            assertEquals(1, cursoRepository.count(), "Deveria haver um cursos no banco");
            var cursoSaved = cursoRepository.findById(idCurso);
            assertTrue(cursoSaved.isPresent());
        }
    }
}