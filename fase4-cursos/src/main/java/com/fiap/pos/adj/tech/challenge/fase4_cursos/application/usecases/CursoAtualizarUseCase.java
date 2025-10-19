package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.validations.CursoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoAtualizarUseCase implements CursoAtualizarInputPort {

    private final CursoAtualizarOutputPort cursoAtualizarOutputPort;

    private final CursoValidation cursoValidation;

    @Override
    public CursoResponse atualizarPorId(UUID id, CursoRequest request) {

        cursoValidation.checkDuplicateNome(id, request.nome());

        var curso = new Curso(id, request.nome());
        return cursoAtualizarOutputPort.atualizar(curso);
    }
}
