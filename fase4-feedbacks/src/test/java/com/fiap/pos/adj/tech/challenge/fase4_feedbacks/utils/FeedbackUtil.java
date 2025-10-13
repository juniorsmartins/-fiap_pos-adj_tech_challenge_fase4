package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.utils;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.FeedbackRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CursoEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.FeedbackEntity;

import java.util.UUID;

public final class FeedbackUtil {

    public static FeedbackRequest montarFeedbackRequest(int nota, String comentario, UUID cursoId, UUID estudanteId) {
        return new FeedbackRequest(nota, comentario, cursoId, estudanteId);
    }

    public static FeedbackEntity montarFeedbackEntity(UUID id, int nota, String comentario, CursoEntity curso, EstudanteEntity estudante) {
        return new FeedbackEntity(id, nota, comentario, curso, estudante);
    }
}
