package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    }

    @Nested
    @DisplayName("Atualizar")
    class Atualizar {

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