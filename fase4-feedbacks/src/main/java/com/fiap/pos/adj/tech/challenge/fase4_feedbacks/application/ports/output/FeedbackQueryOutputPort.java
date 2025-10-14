package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Feedback;

import java.util.Optional;
import java.util.UUID;

public interface FeedbackQueryOutputPort {

    Optional<Feedback> consultarPorId(UUID id);
}
