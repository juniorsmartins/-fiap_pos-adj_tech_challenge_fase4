package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.FeedbackRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;

public interface FeedbackCriarInputPort {

    FeedbackResponse criar(FeedbackRequest request);
}
