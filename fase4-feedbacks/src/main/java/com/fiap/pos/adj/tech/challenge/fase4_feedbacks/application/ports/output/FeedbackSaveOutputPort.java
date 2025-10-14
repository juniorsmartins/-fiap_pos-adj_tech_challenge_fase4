package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Feedback;

public interface FeedbackSaveOutputPort {

    FeedbackResponse save(Feedback feedback);
}
