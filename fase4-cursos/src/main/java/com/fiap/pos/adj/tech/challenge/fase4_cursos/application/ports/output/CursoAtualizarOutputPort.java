package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;

public interface CursoAtualizarOutputPort {

    CursoResponse atualizar(Curso curso);
}
