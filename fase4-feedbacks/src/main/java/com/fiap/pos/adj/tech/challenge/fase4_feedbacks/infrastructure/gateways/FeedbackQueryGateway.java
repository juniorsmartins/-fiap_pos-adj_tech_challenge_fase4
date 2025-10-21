package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.FeedbackQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Feedback;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.FeedbackPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FeedbackQueryGateway implements FeedbackQueryOutputPort {

    private final FeedbackRepository feedbackRepository;

    private final FeedbackPresenter feedbackPresenter;

    @Transactional(readOnly = true)
    @Override
    public Optional<Feedback> consultarPorId(final UUID id) {
        return feedbackRepository.findById(id)
                .map(feedbackPresenter::toFeedback);
    }
}
