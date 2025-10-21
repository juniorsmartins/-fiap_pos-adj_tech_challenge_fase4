package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Feedback;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.FeedbackEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.producer.MessageFeedback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeedbackPresenterImpl implements FeedbackPresenter {

    private final CursoPresenter cursoPresenter;

    private final CustomerPresenter customerPresenter;

    @Override
    public FeedbackEntity toEntity(Feedback feedback) {
        var cursoEntity = cursoPresenter.toEntity(feedback.getCurso());
        var estudanteEntity = customerPresenter.toEntity(feedback.getCustomer());
        return new FeedbackEntity(feedback.getId(), feedback.getNota(), feedback.getComentario(), cursoEntity, estudanteEntity);
    }

    @Override
    public FeedbackResponse toResponse(FeedbackEntity entity) {
        var cursoResponse = cursoPresenter.toResponse(entity.getCurso());
        var estudanteResponse = customerPresenter.toResponse(entity.getCustomer());
        return new FeedbackResponse(entity.getId(), entity.getNota(), entity.getComentario(), cursoResponse, estudanteResponse);
    }

    @Override
    public FeedbackResponse toResponse(Feedback feedback) {
        var cursoResponse = cursoPresenter.toResponse(feedback.getCurso());
        var estudanteResponse = customerPresenter.toResponse(feedback.getCustomer());
        return new FeedbackResponse(feedback.getId(), feedback.getNota(), feedback.getComentario(), cursoResponse, estudanteResponse);
    }

    @Override
    public Feedback toFeedback(FeedbackEntity entity) {
        var curso = cursoPresenter.toCurso(entity.getCurso());
        var estudante = customerPresenter.toModel(entity.getCustomer());
        return new Feedback(entity.getId(), entity.getNota(), entity.getComentario(), curso, estudante);
    }

    @Override
    public MessageFeedback toMessage(FeedbackResponse response) {
        return new MessageFeedback(response.id(), response.nota(), response.comentario(), response.curso().id(), response.customer().id());
    }
}
