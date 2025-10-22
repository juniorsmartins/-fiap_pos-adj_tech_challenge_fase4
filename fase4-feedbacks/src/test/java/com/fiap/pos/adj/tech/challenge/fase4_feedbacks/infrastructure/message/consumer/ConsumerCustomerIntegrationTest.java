package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.consumer;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CustomerRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.BaseIntegrationTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsumerCustomerIntegrationTest extends BaseIntegrationTest {

    @Value("${spring.kafka.topic.event-create-users}")
    private String topicCreateUsers;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Nested
    @DisplayName("ConsumerCustomerValid")
    class ConsumerCustomerValid {

        @Test
        void dadaMensagemValida_quandoAcionadoConsumerCustomer_entaoSalvarNoBancoDeDadosComSucesso() throws InterruptedException {
            var idCustomer = UUID.randomUUID();
            var message = new MessageCustomer(idCustomer, "Jeff Beck", "beck@email.com");
            var key = UUID.randomUUID().toString();

            assertEquals(0, customerRepository.count(), "Deveria haver zero customer no banco");

            kafkaTemplate.send(topicCreateUsers, key, message);
            Thread.sleep(4000);

            assertEquals(1, customerRepository.count(), "Deveria haver um customer no banco");
            var customerSaved = customerRepository.findById(idCustomer);
            assertTrue(customerSaved.isPresent());
        }
    }
}