package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.FeedbackSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Feedback;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.FeedbackPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FeedbackSaveGateway implements FeedbackSaveOutputPort {

    private final FeedbackRepository feedbackRepository;

    private final FeedbackPresenter feedbackPresenter;

    @Transactional
    @Override
    public FeedbackResponse save(Feedback feedback) {

        return Optional.ofNullable(feedback)
                .map(feedbackPresenter::toEntity)
                .map(feedbackRepository::save)
                .map(feedbackPresenter::toResponse)
                .orElseThrow();
    }
}
