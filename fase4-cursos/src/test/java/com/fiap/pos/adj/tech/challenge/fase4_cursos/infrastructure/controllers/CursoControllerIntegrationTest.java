package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.controllers;

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

        cursoEntity = CursoUtil.montarCursoEntity(null, NOME_PADRAO, true);
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
            var request = CursoUtil.montarCursoRequest("Test-Driven Development - TDD");

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
        void dadaRequisicaoInvalidaComNomeDuplicado_quandoAtualizarPorId_entaoLancarExcecao() {
            var cursoEntity = CursoUtil.montarCursoEntity(null, "Administração de Banco de Dados I", true);
            cursoRepository.save(cursoEntity);

            var request = CursoUtil.montarCursoRequest(NOME_PADRAO);

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
        void dadaRequisicaoInvalidaComIdDesativado_quandoAtualizarPorId_entaoLancarExcecao() {
            var cursoEntity = CursoUtil.montarCursoEntity(null, "Redes de Computadores I", false);
            cursoRepository.save(cursoEntity);

            var request = CursoUtil.montarCursoRequest("Redes de Computadores Avançado");

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
    @DisplayName("ApagarPorIdValido")
    class ApagarPorIdValido {

        @Test
        void dadaRequisicaoValidaComIdExistente_quandoApagarPorId_entaoRetornarSucesso() {

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", cursoEntity.getId())
                    .then()
                        .statusCode(HttpStatus.NO_CONTENT.value());
        }

        @Test
        void dadaRequisicaoValida_quandoApagarPorId_entaoArmazenarAtivoFalseNoBancoDeDados() {
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
    @DisplayName("ApagarPorIdInvalido")
    class ApagarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoApagarPorId_entaoLancarExcecao() {
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
        void dadaRequisicaoInvalidaComIdDesativado_quandoApagarPorId_entaoLancarExcecao() {
            var cursoEntity = CursoUtil.montarCursoEntity(null, "Segurança da Informação I", false);
            cursoRepository.save(cursoEntity);

            assertNotNull(cursoEntity.getId());

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
        void dadaRequisicaoInvalidaComIdDesativado_quandoConsultarPorId_entaoLancarExcecao() {
            var cursoEntity = CursoUtil.montarCursoEntity(null, "Engenharia de Software I", false);
            cursoRepository.save(cursoEntity);

            assertNotNull(cursoEntity.getId());

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