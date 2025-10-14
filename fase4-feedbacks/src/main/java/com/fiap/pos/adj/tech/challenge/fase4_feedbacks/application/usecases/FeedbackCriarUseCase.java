package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.EstudanteNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.FeedbackRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.FeedbackCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CursoQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.FeedbackSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Feedback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackCriarUseCase implements FeedbackCriarInputPort {

    private final CursoQueryOutputPort cursoQueryOutputPort;

    private final EstudanteQueryOutputPort estudanteQueryOutputPort;

    private final FeedbackSaveOutputPort feedbackSaveOutputPort;

    @Override
    public FeedbackResponse criar(FeedbackRequest request) {

        var curso = verifyExistenceCourse(request.curso());
        var estudante = verifyExistenceStudent(request.estudante());
        var feedback = new Feedback(null, request.nota(), request.comentario(), curso, estudante);
        return feedbackSaveOutputPort.save(feedback);
    }

    private Curso verifyExistenceCourse(UUID id) {
        return cursoQueryOutputPort.findById(id)
                .orElseThrow(() -> new CursoNotFoundCustomException(id));
    }

    private Estudante verifyExistenceStudent(UUID id) {
        return estudanteQueryOutputPort.findById(id)
                .orElseThrow(() -> new EstudanteNotFoundCustomException(id));
    }
}
