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

import static org.junit.jupiter.api.Assertions.*;

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
                .buildRequest("Jeff Sutherland", EMAIL_TESTE, "11111", RoleEnum.ROLE_CUSTOMER);
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
                    .buildRequest("Robert Martin", "bob@email.com", "11111", RoleEnum.ROLE_CUSTOMER);
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
                        .body("usuario.role.nome", Matchers.equalTo(RoleEnum.ROLE_CUSTOMER.name()));
        }
    }

    @Nested
    @DisplayName("CriarInvalido")
    class CriarInvalido {

        @Test
        void dadaRequisicaoInvalidaComEmailDuplicado_quandoCriar_entaoLancarExcecao() {
            var request = CustomerUtil
                    .buildRequest("Kent Beck", EMAIL_TESTE, "333333", RoleEnum.ROLE_CUSTOMER);

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
            var request = CustomerUtil
                    .buildRequest("Alistair Cockburn", EMAIL_TESTE, "444444", RoleEnum.ROLE_CUSTOMER);
            assertThrows(EmailConflictRulesCustomException.class, () -> customerController.criar(request));
        }
    }

    @Nested
    @DisplayName("AtualizarPorIdValido")
    class AtualizarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoAtualizarPorId_entaoRetornarSucesso() {
            var request = CustomerUtil
                    .buildRequest("Atualizado", "atualizado@email.com", "99999", RoleEnum.ROLE_ADMIN);

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
                        .body("usuario.role.nome", Matchers.equalTo(RoleEnum.ROLE_ADMIN.name()));
        }

        @Test
        void dadaRequisicaoValida_quandoAtualizarPorIdAndMudarRole_entaoAtualizarNoBancoDeDados() {
            var customerDoBancoAntes = customerRepository.findById(customerResponse.id()).get();
            assertEquals(RoleEnum.ROLE_CUSTOMER, customerDoBancoAntes.getUser().getRole().getNome());

            var request = CustomerUtil
                    .buildRequest("Atualizado", "atualizado@email.com", "99999", RoleEnum.ROLE_ADMIN);

            RestAssured.given()
                     .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .put("/{id}", customerResponse.id())
                    .then()
                        .statusCode(HttpStatus.OK.value())
                        .body("usuario.role.nome", Matchers.equalTo(RoleEnum.ROLE_ADMIN.name()));

            var customerDoBancoDepois = customerRepository.findById(customerResponse.id()).get();
            assertEquals(RoleEnum.ROLE_ADMIN, customerDoBancoDepois.getUser().getRole().getNome());
        }
    }

    @Nested
    @DisplayName("AtualizarPorIdInvalido")
    class AtualizarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoAtualizarPorId_entaoLancarException() {
            var idInexistente = UUID.randomUUID();
            var request = CustomerUtil
                    .buildRequest("Martin Fowler", "fowler@email.com", "88188", RoleEnum.ROLE_CUSTOMER);

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
            var request = CustomerUtil
                    .buildRequest("Atualizado", "atualizado@email.com", "99999", RoleEnum.ROLE_CUSTOMER);
            assertThrows(CustomerNotFoundCustomException.class, () -> customerController
                    .atualizarPorId(idInexistente, request));
        }

        @Test
        void dadaRequisicaoInvalidaComEmailDuplicado_quandoAtualizarPorId_entaoLancarException() {
            var requestBase = CustomerUtil
                    .buildRequest("Martin Fowler", "fowler@yahoo.com", "88188", RoleEnum.ROLE_CUSTOMER);
            var response = customerController.criar(requestBase);

            var requestUpdate = CustomerUtil
                    .buildRequest("Martin A. Fowler", EMAIL_TESTE, "777777", RoleEnum.ROLE_CUSTOMER);

            RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(requestUpdate)
                    .when()
                        .put("/{id}", response.getBody().id())
                    .then()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .body("title", Matchers.equalTo("Esse email já existe no sistema: " + EMAIL_TESTE + "."));
        }

        @Test
        void dadaRequisicaoInvalidaComEmailDuplicado_quandoAtualizarPorId_entaoLancarEmailConflictRulesCustomException() {
            var requestBase = CustomerUtil
                    .buildRequest("Martin Fowler", "fowler@yahoo.com", "88188", RoleEnum.ROLE_CUSTOMER);
            var response = customerController.criar(requestBase);

            var requestUpdate = CustomerUtil
                    .buildRequest("Martin A. Fowler", EMAIL_TESTE, "777777", RoleEnum.ROLE_CUSTOMER);

            assertThrows(EmailConflictRulesCustomException.class, () ->
                    customerController.atualizarPorId(response.getBody().id(), requestUpdate));
        }
    }

    @Nested
    @DisplayName("DesativarPorIdValido")
    class DesativarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoDesativarPorId_entaoRetornarSucesso() {

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", customerResponse.id())
                    .then()
                        .statusCode(HttpStatus.NO_CONTENT.value());
        }

        @Test
        void dadaRequisicaoValida_quandoDesativarPorId_entaoArmazenarAtivoFalseNoBancoDeDados() {

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", customerResponse.id())
                    .then()
                        .statusCode(HttpStatus.NO_CONTENT.value());

            var customerOptional = customerRepository.findById(customerResponse.id());
            Assertions.assertTrue(customerOptional.isPresent());
            Assertions.assertFalse(customerOptional.get().isAtivo());
        }
    }

    @Nested
    @DisplayName("DesativarPorIdInvalido")
    class DesativarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdInexistente_quandoDesativarPorId_entaoLancarException() {
            var idInexistente = UUID.randomUUID();

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", idInexistente)
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Cliente não encontrado por id: " + idInexistente + "."));
        }

        @Test
        void dadaRequisicaoInvalida_quandoDesativarPorId_entaoLancarCustomerNotFoundCustomException() {
            var idInexistente = UUID.randomUUID();
            assertThrows(CustomerNotFoundCustomException.class, () -> customerController.desativarPorId(idInexistente));
        }

        @Test
        void dadaRequisicaoInvalidaComIdDesativado_quandoDesativarPorId_entaoLancarException() {
            var customerBuscadoAntes = customerController.consultarPorId(customerResponse.id());
            assertNotNull(customerBuscadoAntes.getBody());

            var customerDesativado = customerController.desativarPorId(customerResponse.id());
            assertEquals(HttpStatus.NO_CONTENT, customerDesativado.getStatusCode());

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .delete("/{id}", customerResponse.id())
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .body("title", Matchers.equalTo("Cliente não encontrado por id: " + customerResponse.id() + "."));
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdValido")
    class ConsultarPorIdValido {

        @Test
        void dadaRequisicaoValida_quandoConsultarPorId_entaoRetornarSucesso() {

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .get("/{id}", customerResponse.id())
                    .then()
                        .statusCode(HttpStatus.OK.value())
                        .body("id", Matchers.equalTo(customerResponse.id().toString()))
                        .body("nome", Matchers.equalTo(customerResponse.nome()))
                        .body("usuario.email", Matchers.equalTo(customerResponse.usuario().email()));
        }
    }

    @Nested
    @DisplayName("ConsultarPorIdInvalido")
    class ConsultarPorIdInvalido {

        @Test
        void dadaRequisicaoInvalida_quandoConsultarPorId_entaoLancarExcecao() {
            var idInexistente = UUID.randomUUID();

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .get("/{id}", idInexistente)
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Cliente não encontrado por id: " + idInexistente + "."));
        }

        @Test
        void dadaRequisicaoInvalida_quandoConsultarPorId_entaoLancarCustomerNotFoundCustomException() {
            var idInexistente = UUID.randomUUID();
            assertThrows(CustomerNotFoundCustomException.class, () -> customerController.consultarPorId(idInexistente));
        }

        @Test
        void dadaRequisicaoInvalidaComIdDesativado_quandoConsultarPorId_entaoLancarExcecao() {
            var customerBuscadoAntes = customerController.consultarPorId(customerResponse.id());
            assertNotNull(customerBuscadoAntes.getBody());

            var customerDesativado = customerController.desativarPorId(customerResponse.id());
            assertEquals(HttpStatus.NO_CONTENT, customerDesativado.getStatusCode());

            RestAssured.given()
                        .contentType(ContentType.JSON)
                    .when()
                        .get("/{id}", customerResponse.id())
                    .then()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .body("title", Matchers.equalTo("Cliente não encontrado por id: " + customerResponse.id() + "."));
        }
    }
}