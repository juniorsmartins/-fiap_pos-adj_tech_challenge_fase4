package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http404.CustomerNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http409.EmailConflictRulesCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories.CustomerRepository;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories.RoleRepository;
import com.fiap.pos.adj.tech.challenge.fase4_users.utils.BaseIntegrationTest;
import com.fiap.pos.adj.tech.challenge.fase4_users.utils.CustomerUtil;
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
class CustomerControllerIntegrationTest extends BaseIntegrationTest {

    private static final String URI_CUSTOMER = "/v1/customers";

    private static final String EMAIL_TESTE = "teste@email.com";

    @LocalServerPort
    private int randomPort;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp() {
        RestAssured.port = randomPort; // Configura a porta dinâmica
        RestAssured.basePath = URI_CUSTOMER;

        var request = CustomerUtil
                .buildRequest("Jeff Sutherland", EMAIL_TESTE, "11111");
        customerResponse = customerController.criar(request).getBody();
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Nested
    @DisplayName("CriarValido")
    class CriarValido {

        @Test
        void dadaRequisicaoValida_quandoCriar_entaoRetornarSucesso() {
            // Arrange
            var request = CustomerUtil
                    .buildRequest("Robert Martin", "bob@email.com", "11111");
            // Act And Assert
            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .post()
                    .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .body("nome", Matchers.equalTo(request.nome()))
                        .body("usuario.email", Matchers.equalTo(request.email()))
                        .body("usuario.password", Matchers.equalTo(request.password()))
                        .body("usuario.role.nome", Matchers.equalTo(RoleEnum.ROLE_ESTUDANTE.name()));
        }
    }

    @Nested
    @DisplayName("CriarInvalido")
    class CriarInvalido {

        @Test
        void dadaRequisicaoInvalidaComEmailDuplicado_quandoCriar_entaoLancarExcecao() {
            var request = CustomerUtil
                    .buildRequest("Kent Beck", EMAIL_TESTE, "333333");

            RestAssured.given()
                            .contentType(ContentType.JSON)
                            .body(request)
                        .when()
                            .post()
                        .then()
                            .statusCode(HttpStatus.CONFLICT.value())
                            .body("title", Matchers.equalTo("Esse email já existe no sistema: " + EMAIL_TESTE + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComEmailDuplicado_quandoCriar_entaoLancarEmailConflictRulesCustomException() {
            var request = CustomerUtil.buildRequest("Alistair Cockburn", EMAIL_TESTE, "444444");
            assertThrows(EmailConflictRulesCustomException.class, () -> customerController.criar(request));
        }
    }

    @Nested
    @DisplayName("AtualizarPorIdValido")
    class AtualizarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoAtualizarPorId_entaoRetornarSucesso() {
            var request = CustomerUtil
                    .buildRequest("Atualizado", "atualizado@email.com", "99999");

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .put("/{id}", customerResponse.id())
                    .then()
                        .statusCode(HttpStatus.OK.value())
                        .body("nome", Matchers.equalTo(request.nome()))
                        .body("usuario.email", Matchers.equalTo(request.email()))
                        .body("usuario.password", Matchers.equalTo(request.password()))
                        .body("usuario.role.nome", Matchers.equalTo(RoleEnum.ROLE_ESTUDANTE.name()));
        }
    }

    @Nested
    @DisplayName("AtualizarPorIdInvalido")
    class AtualizarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoAtualizarPorId_entaoLancarException() {
            var idInexistente = UUID.randomUUID();
            var request = CustomerUtil
                    .buildRequest("Martin Fowler", "fowler@email.com", "88188");

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .put("/{id}", idInexistente)
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Cliente não encontrado por id: " + idInexistente + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoAtualizarPorId_entaoLancarCustomerNotFoundCustomException() {
            var idInexistente = UUID.randomUUID();
            var request = CustomerUtil.buildRequest("Atualizado", "atualizado@email.com", "99999");
            assertThrows(CustomerNotFoundCustomException.class, () -> customerController
                    .atualizarPorId(idInexistente, request));
        }
    }

    @Nested
    @DisplayName("ApagarPorIdValido")
    class ApagarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoApagarPorId_entaoRetornarRespontaComSucesso() {
            var response = customerController.apagarPorId(customerResponse.id());
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("ApagarPorIdInvalido")
    class ApagarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalida_quandoApagarPorId_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();
            assertThrows(CustomerNotFoundCustomException.class, () -> customerController
                    .apagarPorId(idInexistente));
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdValido")
    class ConsultarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoConsultarPorId_entaoRetornarSucesso() {
            var response = customerController.consultarPorId(customerResponse.id());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            var body = response.getBody();
            assertEquals(customerResponse.id(), body.id());
            assertEquals(customerResponse.nome(), body.nome());
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdInvalido")
    class ConsultarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalida_quandoConsultarPorId_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();
            assertThrows(CustomerNotFoundCustomException.class, () -> customerController
                    .consultarPorId(idInexistente));
        }
    }
}