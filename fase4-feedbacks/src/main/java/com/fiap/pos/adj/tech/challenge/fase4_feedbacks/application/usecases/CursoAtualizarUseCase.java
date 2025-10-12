package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.CursoAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CursoAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Curso;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoAtualizarUseCase implements CursoAtualizarInputPort {

    private final CursoAtualizarOutputPort cursoAtualizarOutputPort;

    @Override
    public CursoResponse atualizarPorId(UUID id, CursoRequest request) {

        var curso = new Curso(id, request.nome());
        return cursoAtualizarOutputPort.atualizar(curso);
    }
}
