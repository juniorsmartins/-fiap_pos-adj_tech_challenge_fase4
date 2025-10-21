package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.CustomerNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.FeedbackNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CustomerEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.FeedbackEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CursoRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CustomerRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.FeedbackRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.BaseIntegrationTest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.CursoUtil;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.CustomerUtil;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.FeedbackUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeedbackControllerIntegrationTest extends BaseIntegrationTest {

    private static final String URI_FEEDBACK = "/v1/feedbacks";

    @LocalServerPort
    private int randomPort;

    @Autowired
    private FeedbackController feedbackController;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private FeedbackEntity feedbackEntity1;

    private CursoEntity cursoEntity1;

    private CursoEntity cursoEntity2;

    private CustomerEntity customerEntity1;



    @BeforeEach
    void setUp() {
        RestAssured.port = randomPort; // Configura a porta dinâmica
        RestAssured.basePath = URI_FEEDBACK;

        customerEntity1 = CustomerUtil.buildEntity(UUID.randomUUID());
        customerRepository.saveAndFlush(customerEntity1);

        cursoEntity1 = CursoUtil.buildEntity(UUID.randomUUID());
        cursoEntity2 = CursoUtil.buildEntity(UUID.randomUUID());
        cursoRepository.saveAll(List.of(cursoEntity1, cursoEntity2));

        feedbackEntity1 = FeedbackUtil.buildEntity(null, 5, "Ótimo curso!", cursoEntity1, customerEntity1);
        feedbackRepository.save(feedbackEntity1);
    }

    @AfterEach
    void tearDown() {
        feedbackRepository.deleteAll();
        cursoRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Nested
    @DisplayName("CriarValido")
    class CriarValido {

        @Test
        void dadaRequisicaoValida_quandoCriar_entaoRetornarSucesso() {
            var request = FeedbackUtil
                    .buildRequest(1, "O professor sempre chega atrasado.", cursoEntity1.getId(), customerEntity1.getId());

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .post()
                    .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .body("nota", Matchers.equalTo(request.nota()))
                        .body("comentario", Matchers.equalTo(request.comentario()))
                        .body("curso.id", Matchers.equalTo(request.curso().toString()))
                        .body("customer.id", Matchers.equalTo(request.customer().toString()));
        }
    }

    @Nested
    @DisplayName("CriarInvalido")
    class CriarInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdCursoInexistente_quandoCriar_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();
            var request = FeedbackUtil
                    .buildRequest(4, "Provas muito fáceis.", idInexistente, customerEntity1.getId());

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .post()
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Curso não encontrado por id: " + idInexistente + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComIdCursoInexistente_quandoCriar_entaoLancarCursoNotFoundCustomException() {
            assertThrows(CursoNotFoundCustomException.class, () -> {
                var idInexistente = UUID.randomUUID();
                var request = FeedbackUtil
                        .buildRequest(2, "O professor não domina o conteúdo.", idInexistente, customerEntity1.getId());
                feedbackController.criar(request);
            });
        }

        @Test
        void dadaRequisicaoInvalidaComIdCustomerInexistente_quandoCriar_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();
            var request = FeedbackUtil
                    .buildRequest(5, "Provas de ótima qualidade.", cursoEntity1.getId(), idInexistente);

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .post()
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Cliente não encontrado por id: " + idInexistente + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComIdCustomerInexistente_quandoCriar_entaoLancarCustomerNotFoundCustomException() {
            assertThrows(CustomerNotFoundCustomException.class, () -> {
                var idInexistente = UUID.randomUUID();
                var request = FeedbackUtil
                        .buildRequest(2, "O professor não domina o conteúdo.", cursoEntity2.getId(), idInexistente);
                feedbackController.criar(request);
            });
        }
    }

    @Nested
    @DisplayName("ApagarPorIdValido")
    class ApagarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoApagarPorId_entaoRetornarSucesso() {
            var response = feedbackController.apagarPorId(feedbackEntity1.getId());
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("ApagarPorIdInvalido")
    class ApagarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoApagarPorId_entaoLancarExcecao() {
            assertThrows(FeedbackNotFoundCustomException.class, () -> {
                feedbackController.apagarPorId(UUID.randomUUID());
            });
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdValido")
    class ConsultarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoConsultarPorId_entaoRetornarSucesso() {
            var response = feedbackController.consultarPorId(feedbackEntity1.getId());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            var body = response.getBody();
            assertEquals(feedbackEntity1.getId(), body.id());
            assertEquals(feedbackEntity1.getNota(), body.nota());
            assertEquals(feedbackEntity1.getComentario(), body.comentario());
            assertEquals(feedbackEntity1.getCurso().getId(), body.curso().id());
            assertEquals(feedbackEntity1.getCustomer().getId(), body.customer().id());
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdInvalido")
    class ConsultarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoConsultarPorId_entaoLancarExcecao() {
            assertThrows(FeedbackNotFoundCustomException.class, () -> {
                var idInexistente = UUID.randomUUID();
                feedbackController.consultarPorId(idInexistente);
            });
        }
    }
}