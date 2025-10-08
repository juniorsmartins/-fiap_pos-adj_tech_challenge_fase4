package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.EstudanteNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http409.EmailConflictRulesCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.EstudanteRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.RoleRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.EstudanteUtil;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.PapelUtil;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.UsuarioUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class EstudanteControllerIntegrationTest {

    private static final String EMAIL_TESTE = "teste@email.com";

    @Autowired
    private EstudanteController estudanteController;

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private RoleRepository roleRepository;

    private EstudanteEntity estudanteEntity;

    @BeforeEach
    void setUp() {
        var papelEntity = PapelUtil.montarRoleEntity(null, RoleEnum.ROLE_ESTUDANTE);
        roleRepository.save(papelEntity);

        var usuarioEntity = UsuarioUtil.montarUserEntity(null, EMAIL_TESTE, "55555", papelEntity);
        var studentEntity = EstudanteUtil.montarEstudanteEntity(null, "Teste Teste", usuarioEntity);
        estudanteEntity = estudanteRepository.save(studentEntity);
    }

    @AfterEach
    void tearDown() {
        estudanteRepository.deleteAll();
    }

    @Nested
    @DisplayName("CriarValido")
    class CriarValido {

        @Test
        void dadaRequisicaoValida_quandoCriar_entaoRetornarRespostaValida() {
            // Arrange
            var request = EstudanteUtil.montarEstudanteRequest("Jeff Sutherland", "jeff@email.com", "11111");
            // Act
            var response = estudanteController.criar(request);
            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            var body = response.getBody();
            assertEquals(request.nome(), body.nome());
            assertEquals(request.email(), body.usuario().email());
            assertEquals(request.password(), body.usuario().password());
            assertEquals(RoleEnum.ROLE_ESTUDANTE, body.usuario().role().nome());
        }
    }

    @Nested
    @DisplayName("CriarInvalido")
    class CriarInvalido {

        @Test
        void dadaRequisicaoInvalidaComEmailDuplicado_quandoCriar_entaoLancarExcecao() {
            // Arrange
            var request = EstudanteUtil.montarEstudanteRequest("Jeff Sutherland", EMAIL_TESTE, "22222");
            // Act & Assert
            assertThrows(EmailConflictRulesCustomException.class, () -> estudanteController.criar(request));
        }
    }

    @Nested
    @DisplayName("AtualizarPorId")
    class AtualizarPorId {

    }

    @Nested
    @DisplayName("ApagarPorIdValido")
    class ApagarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoApagarPorId_entaoRetornarRespontaComSucesso() {
            var response = estudanteController.apagarPorId(estudanteEntity.getId());
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("ApagarPorIdInvalido")
    class ApagarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalida_quandoApagarPorId_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();
            assertThrows(EstudanteNotFoundCustomException.class, () -> estudanteController
                    .apagarPorId(idInexistente));
        }
    }

    @Nested
    @DisplayName("ConsultarPorId")
    class ConsultarPorId {

    }
}