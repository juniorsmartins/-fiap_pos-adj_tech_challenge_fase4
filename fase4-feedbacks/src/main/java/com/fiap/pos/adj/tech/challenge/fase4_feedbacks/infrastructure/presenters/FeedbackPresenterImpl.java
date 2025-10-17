package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Feedback;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.FeedbackEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.producer.FeedbackKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeedbackPresenterImpl implements FeedbackPresenter {

    private final CursoPresenter cursoPresenter;

    private final EstudantePresenter estudantePresenter;

    @Override
    public FeedbackEntity toEntity(Feedback feedback) {
        var cursoEntity = cursoPresenter.toEntity(feedback.getCurso());
        var estudanteEntity = estudantePresenter.toEntity(feedback.getEstudante());
        return new FeedbackEntity(feedback.getId(), feedback.getNota(), feedback.getComentario(), cursoEntity, estudanteEntity);
    }

    @Override
    public FeedbackResponse toResponse(FeedbackEntity entity) {
        var cursoResponse = cursoPresenter.toResponse(entity.getCurso());
        var estudanteResponse = estudantePresenter.toResponse(entity.getEstudante());
        return new FeedbackResponse(entity.getId(), entity.getNota(), entity.getComentario(), cursoResponse, estudanteResponse);
    }

    @Override
    public FeedbackResponse toResponse(Feedback feedback) {
        var cursoResponse = cursoPresenter.toResponse(feedback.getCurso());
        var estudanteResponse = estudantePresenter.toResponse(feedback.getEstudante());
        return new FeedbackResponse(feedback.getId(), feedback.getNota(), feedback.getComentario(), cursoResponse, estudanteResponse);
    }

    @Override
    public Feedback toFeedback(FeedbackEntity entity) {
        var curso = cursoPresenter.toCurso(entity.getCurso());
        var estudante = estudantePresenter.toModel(entity.getEstudante());
        return new Feedback(entity.getId(), entity.getNota(), entity.getComentario(), curso, estudante);
    }

    @Override
    public FeedbackKafka toKafka(FeedbackResponse response) {
        return new FeedbackKafka(response.id(), response.nota(), response.comentario(), response.curso().nome(), response.estudante().nome());
    }
}
