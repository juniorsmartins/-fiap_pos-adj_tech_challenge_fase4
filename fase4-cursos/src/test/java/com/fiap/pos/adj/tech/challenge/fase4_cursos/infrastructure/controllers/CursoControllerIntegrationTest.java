package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http409.NomeDuplicatedCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.repositories.CursoRepository;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.utils.BaseIntegrationTest;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.utils.CursoUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CursoControllerIntegrationTest extends BaseIntegrationTest {

    private static final String URI_CURSOS = "/v1/cursos";

    private static final String NOME_PADRAO = "Tecnologia Java";

    @LocalServerPort
    private int randomPort;

    @Autowired
    private CursoController cursoController;

    @Autowired
    private CursoRepository cursoRepository;

    private CursoEntity cursoEntity;

    @BeforeEach
    void setUp() {
        RestAssured.port = randomPort; // Configura a porta dinâmica
        RestAssured.basePath = URI_CURSOS;

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

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .post()
                    .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .body("nome", Matchers.equalTo(request.nome()));
        }
    }

    @Nested
    @DisplayName("CriarInvalido")
    class CriarInvalido {

        @Test
        void dadaRequisicaoInvalidaComNomeDuplicado_quandoCriar_entaoLancarExcecao() {
            var request = CursoUtil.montarCursoRequest(NOME_PADRAO);

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post()
                .then()
                    .statusCode(HttpStatus.CONFLICT.value())
                    .body("title", Matchers.equalTo("Esse nome já existe no sistema: " + NOME_PADRAO + "."));
        }
    }

    @Nested
    @DisplayName("AtualizarPorIdValido")
    class AtualizarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoAtualizarPorId_entaoRetornarSucesso() {
            var request = CursoUtil.montarCursoRequest("Domain-Driven Design - DDD");
            var response = cursoController.atualizarPorId(cursoEntity.getId(), request);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            var body = response.getBody();
            assertEquals(request.nome(), body.nome());
        }
    }

    @Nested
    @DisplayName("AtualizarPorIdInvalido")
    class AtualizarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoAtualizarPorId_entaoLancarExcecao() {
            assertThrows(CursoNotFoundCustomException.class, () -> {
                var idInexistente = UUID.randomUUID();
                var requestValido = CursoUtil.montarCursoRequest("Jornada Microsserviços");
                cursoController.atualizarPorId(idInexistente, requestValido);
            });
        }

        @Test
        void dadaRequisicaoInvalidaComNomeDuplicado_quandoAtualizarPorId_entaoLancarExcecao() {
            var request = CursoUtil.montarCursoRequest("Administração de Banco de Dados I");
            var response = cursoController.criar(request);

            assertThrows(NomeDuplicatedCustomException.class, () -> {
                var requestValido = CursoUtil.montarCursoRequest(NOME_PADRAO);
                cursoController.atualizarPorId(response.getBody().id(), requestValido);
            });
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