package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CursoResponse;

public interface CursoCriarInputPort {

    CursoResponse criar(CursoRequest request);
}
