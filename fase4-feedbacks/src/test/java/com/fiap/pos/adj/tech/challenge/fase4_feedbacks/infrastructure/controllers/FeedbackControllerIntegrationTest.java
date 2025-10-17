package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.EstudanteNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.FeedbackNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.FeedbackEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CursoRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.EstudanteRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.FeedbackRepository;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils.*;
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
class FeedbackControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private FeedbackController feedbackController;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    private FeedbackEntity feedbackEntity1;

    private EstudanteEntity estudanteEntity1;

    private CursoEntity cursoEntity2;

    @BeforeEach
    void setUp() {
        estudanteEntity1 = EstudanteUtil.montarEstudanteEntity(null, "Teste Teste", "teste99@email.com");
        estudanteRepository.save(estudanteEntity1);

        var cursoEntity1 = CursoUtil.montarCursoEntity(null, "Arquitetura e Desenvolvimento Java");
        cursoRepository.save(cursoEntity1);
        cursoEntity2 = CursoUtil.montarCursoEntity(null, "Arquitetura de Software em Java");
        cursoRepository.save(cursoEntity2);

        feedbackEntity1 = FeedbackUtil.montarFeedbackEntity(null, 5, "Ótimo curso!", cursoEntity1, estudanteEntity1);
        feedbackRepository.save(feedbackEntity1);
    }

    @AfterEach
    void tearDown() {
        feedbackRepository.deleteAll();
    }

    @Nested
    @DisplayName("CriarValido")
    class CriarValido {

        @Test
        void dadaRequisicaoValida_quandoCriar_entaoRetornarSucesso() {
            var request = FeedbackUtil.montarFeedbackRequest(3, "O professor sempre chega atrasado.", cursoEntity2.getId(), estudanteEntity1.getId());
            var response = feedbackController.criar(request);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            var body = response.getBody();
            assertEquals(request.nota(), body.nota());
            assertEquals(request.comentario(), body.comentario());
        }
    }

    @Nested
    @DisplayName("CriarInvalido")
    class CriarInvalido {

        @Test
        void dadaRequisicaoInvalidaComIdCursoInexistente_quandoCriar_entaoLancarExcecao() {
            assertThrows(CursoNotFoundCustomException.class, () -> {
                var idInexistente = UUID.randomUUID();
                var request = FeedbackUtil
                        .montarFeedbackRequest(2, "O professor não domina o conteúdo.", idInexistente, estudanteEntity1.getId());
                feedbackController.criar(request);
            });
        }

        @Test
        void dadaRequisicaoInvalidaComIdEstudanteInexistente_quandoCriar_entaoLancarExcecao() {
            assertThrows(EstudanteNotFoundCustomException.class, () -> {
                var idInexistente = UUID.randomUUID();
                var request = FeedbackUtil
                        .montarFeedbackRequest(2, "O professor não domina o conteúdo.", cursoEntity2.getId(), idInexistente);
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
            assertEquals(feedbackEntity1.getEstudante().getId(), body.estudante().id());
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