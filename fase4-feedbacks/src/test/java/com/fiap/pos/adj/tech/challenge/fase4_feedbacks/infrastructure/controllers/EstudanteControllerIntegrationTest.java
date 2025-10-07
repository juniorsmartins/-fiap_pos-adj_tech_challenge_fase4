package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.EstudanteUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class EstudanteControllerIntegrationTest {

    @Autowired
    private EstudanteController estudanteController;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Nested
    @DisplayName("Criar")
    class Criar {

        @Test
        void dadaRequisicaoValida_quandoCriar_entaoRetornarRespostaValida() {
            // Arrange
            var request = EstudanteUtil.montarEstudanteRequest("Jeff Sutherland", "jeff@email.com", "111");
            // Act
            var response = estudanteController.criar(request);
            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(request.nome(), response.nome());
            assertEquals(request.email(), response.usuario().email());
            assertEquals(request.password(), response.usuario().passord());
            assertEquals(RoleEnum.ESTUDANTE, response.usuario().role().nome());
        }
    }

    @Nested
    @DisplayName("AtualizarPorId")
    class AtualizarPorId {

    }

    @Nested
    @DisplayName("ApagarPorId")
    class ApagarPorId {

    }

    @Nested
    @DisplayName("ConsultarPorId")
    class ConsultarPorId {

    }
}