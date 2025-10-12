package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http409.NomeDuplicatedCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CursoRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.CursoUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CursoControllerIntegrationTest {

    private static final String NOME_PADRAO = "Tecnologia Java";

    @Autowired
    private CursoController cursoController;

    @Autowired
    private CursoRepository cursoRepository;

    private CursoEntity cursoEntity;

    @BeforeEach
    void setUp() {
        cursoEntity = CursoUtil.montarCursoEntity(null, NOME_PADRAO);
        cursoRepository.save(cursoEntity);
    }

    @AfterEach
    void tearDown() {
        cursoRepository.deleteAll();
    }

    @Nested
    @DisplayName("CriarValido")
    class CriarValido {

        @Test
        void dadaRequisicaoValida_quandoCriar_entaoRetornarSucesso() {
            var request = CursoUtil.montarCursoRequest("Programação Orientada a Objeto I");
            var response = cursoController.criar(request);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            var body = response.getBody();
            assertEquals(request.nome(), body.nome());
        }
    }

    @Nested
    @DisplayName("CriarInvalido")
    class CriarInvalido {

        @Test
        void dadaRequisicaoInvalidaComNomeDuplicado_quandoCriar_entaoLancarExcecao() {
            var request = CursoUtil.montarCursoRequest(NOME_PADRAO);
            assertThrows(NomeDuplicatedCustomException.class, () -> cursoController.criar(request));
        }
    }

    @Nested
    @DisplayName("ApagarPorIdValido")
    class ApagarPorIdValido {

        @Test
        void dadaRequisicaoValidaComIdExistente_quandoApagarPorId_entaoRetornarSucesso() {
            var response = cursoController.apagarPorId(cursoEntity.getId());
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("ApagarPorIdInvalido")
    class ApagarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoApagarPorId_entaoLancarExcecao() {
            assertThrows(CursoNotFoundCustomException.class, () -> {
                    var idInexistente = UUID.randomUUID();
                    cursoController.apagarPorId(idInexistente);
            });
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdValido")
    class ConsultarPorIdValido {

        @Test
        void dadaRequisicaoValidaComIdExistente_quandoConsultarPorId_entaoRetornarSucesso() {
            var response = cursoController.consultarPorId(cursoEntity.getId());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdInvalido")
    class ConsultarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoConsultarPorId_entaoLancarExcecao() {
            assertThrows(CursoNotFoundCustomException.class, () -> {
                var idInexistente = UUID.randomUUID();
                cursoController.consultarPorId(idInexistente);
            });
        }
    }
}
