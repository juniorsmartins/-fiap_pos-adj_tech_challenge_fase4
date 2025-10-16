package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;

public interface CursoCriarInputPort {

    CursoResponse criar(CursoRequest request);
}
