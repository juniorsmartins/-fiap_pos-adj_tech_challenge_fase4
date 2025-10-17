package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Feedback;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.FeedbackEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.producer.FeedbackKafka;

public interface FeedbackPresenter {

    FeedbackEntity toEntity(Feedback feedback);

    FeedbackResponse toResponse(FeedbackEntity entity);

    FeedbackResponse toResponse(Feedback feedback);

    Feedback toFeedback(FeedbackEntity entity);

    FeedbackKafka toKafka(FeedbackResponse response);
}
