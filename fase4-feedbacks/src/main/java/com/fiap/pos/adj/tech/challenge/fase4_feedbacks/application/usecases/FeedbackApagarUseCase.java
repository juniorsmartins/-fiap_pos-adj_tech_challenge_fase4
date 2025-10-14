package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.FeedbackNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.FeedbackApagarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.FeedbackApagarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.FeedbackQueryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackApagarUseCase implements FeedbackApagarInputPort {

    private final FeedbackQueryOutputPort feedbackQueryOutputPort;

    private final FeedbackApagarOutputPort feedbackApagarOutputPort;

    @Override
    public void apagarPorId(UUID id) {
        feedbackQueryOutputPort.consultarPorId(id)
                .ifPresentOrElse(feedback -> feedbackApagarOutputPort.apagarPorId(feedback.getId()), () -> {
                    throw new FeedbackNotFoundCustomException(id);
                });
    }
}
