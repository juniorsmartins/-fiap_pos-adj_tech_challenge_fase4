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

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        cursoEntity = CursoUtil.buildEntity(null, NOME_PADRAO, true);
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
            var request = CursoUtil.buildRequest("Programação Orientada a Objeto I");

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
            var request = CursoUtil.buildRequest(NOME_PADRAO);

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .post()
                    .then()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .body("title", Matchers.equalTo("Esse nome já existe no sistema: " + NOME_PADRAO + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComNomeDuplicado_quandoCriar_entaoLancarNomeDuplicatedCustomException() {
            var request = CursoUtil.buildRequest(NOME_PADRAO);
            assertThrows(NomeDuplicatedCustomException.class, () -> cursoController.criar(request));
        }
    }

    @Nested
    @DisplayName("AtualizarPorIdValido")
    class AtualizarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoAtualizarPorId_entaoRetornarSucesso() {
            var request = CursoUtil.buildRequest("Domain-Driven Design - DDD");

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .put("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.OK.value())
                        .body("nome", Matchers.equalTo(request.nome()));
        }
    }

    @Nested
    @DisplayName("AtualizarPorIdInvalido")
    class AtualizarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoAtualizarPorId_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();
            var request = CursoUtil.buildRequest("Test-Driven Development - TDD");

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .put("/{id}", idInexistente)
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Curso não encontrado por id: " + idInexistente + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoAtualizarPorId_entaoLancarCursoNotFoundCustomException() {
            var idInexistente = UUID.randomUUID();
            var request = CursoUtil.buildRequest("Behavior-Driven Development - BDD");
            assertThrows(CursoNotFoundCustomException.class, () -> cursoController.atualizarPorId(idInexistente, request));
        }

        @Test
        void dadaRequisicaoInvalidaComNomeDuplicado_quandoAtualizarPorId_entaoLancarExcecao() {
            var cursoEntity = CursoUtil.buildEntity(null, "Administração de Banco de Dados I", true);
            cursoRepository.save(cursoEntity);

            var request = CursoUtil.buildRequest(NOME_PADRAO);

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .put("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .body("title", Matchers.equalTo("Esse nome já existe no sistema: " + NOME_PADRAO + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComNomeDuplicado_quandoAtualizarPorId_entaoLancarNomeDuplicatedCustomException() {
            var cursoEntity = CursoUtil.buildEntity(null, "Python na Análise de Dados", true);
            cursoRepository.save(cursoEntity);

            var request = CursoUtil.buildRequest(NOME_PADRAO);
            assertThrows(NomeDuplicatedCustomException.class, () -> cursoController
                    .atualizarPorId(cursoEntity.getId(), request));
        }

        @Test
        void dadaRequisicaoInvalidaComIdDesativado_quandoAtualizarPorId_entaoLancarExcecao() {
            var cursoEntity = CursoUtil.buildEntity(null, "Redes de Computadores I", false);
            cursoRepository.save(cursoEntity);

            var request = CursoUtil.buildRequest("Redes de Computadores Avançado");

            assertNotNull(cursoEntity.getId());

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .put("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Curso não encontrado por id: " + cursoEntity.getId() + "."));
        }
    }

    @Nested
    @DisplayName("DesativarPorIdValido")
    class DesativarPorIdValido {

        @Test
        void dadaRequisicaoValidaComIdExistente_quandoDesativarPorId_entaoRetornarSucesso() {

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.NO_CONTENT.value());
        }

        @Test
        void dadaRequisicaoValida_quandoDesativarPorId_entaoArmazenarAtivoFalseNoBancoDeDados() {
            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.NO_CONTENT.value());

            var cursoOptional = cursoRepository.findById(cursoEntity.getId());
            Assertions.assertTrue(cursoOptional.isPresent());
            Assertions.assertFalse(cursoOptional.get().isAtivo());
        }
    }

    @Nested
    @DisplayName("DesativarPorIdInvalido")
    class DesativarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoDesativarPorId_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", idInexistente)
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Curso não encontrado por id: " + idInexistente + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoDesativarPorId_entaoLancarCursoNotFoundCustomException() {
            var idInexistente = UUID.randomUUID();
            assertThrows(CursoNotFoundCustomException.class, () -> cursoController.desativarPorId(idInexistente));
        }

        @Test
        void dadaRequisicaoInvalidaComIdDesativado_quandoDesativarPorId_entaoLancarExcecao() {
            var cursoEntity = CursoUtil.buildEntity(null, "Segurança da Informação I", false);
            cursoRepository.save(cursoEntity);

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Curso não encontrado por id: " + cursoEntity.getId() + "."));
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdValido")
    class ConsultarPorIdValido {

        @Test
        void dadaRequisicaoValidaComIdExistente_quandoConsultarPorId_entaoRetornarSucesso() {

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .get("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.OK.value())
                        .body("id", Matchers.equalTo(cursoEntity.getId().toString()))
                        .body("nome", Matchers.equalTo(NOME_PADRAO));
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdInvalido")
    class ConsultarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoConsultarPorId_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .get("/{id}", idInexistente)
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("title", Matchers.equalTo("Curso não encontrado por id: " + idInexistente + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoConsultarPorId_entaoLancarCursoNotFoundCustomException() {
            var idInexistente = UUID.randomUUID();
            assertThrows(CursoNotFoundCustomException.class, () -> cursoController.consultarPorId(idInexistente));
        }

        @Test
        void dadaRequisicaoInvalidaComIdDesativado_quandoConsultarPorId_entaoLancarExcecao() {
            var cursoEntity = CursoUtil.buildEntity(null, "Engenharia de Software I", false);
            cursoRepository.save(cursoEntity);

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .get("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Curso não encontrado por id: " + cursoEntity.getId() + "."));
        }
    }
}