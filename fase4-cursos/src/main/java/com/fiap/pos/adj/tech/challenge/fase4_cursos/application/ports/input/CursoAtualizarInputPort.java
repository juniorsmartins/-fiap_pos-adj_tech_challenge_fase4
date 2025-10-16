package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;

import java.util.UUID;

public interface CursoAtualizarInputPort {

    CursoResponse atualizarPorId(UUID id, CursoRequest request);
}
