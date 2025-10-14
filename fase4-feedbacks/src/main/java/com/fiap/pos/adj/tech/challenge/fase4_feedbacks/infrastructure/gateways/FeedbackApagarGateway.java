package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.FeedbackApagarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FeedbackApagarGateway implements FeedbackApagarOutputPort {

    private final FeedbackRepository feedbackRepository;

    @Transactional
    @Override
    public void apagarPorId(UUID id) {
        feedbackRepository.deleteById(id);
    }
}
