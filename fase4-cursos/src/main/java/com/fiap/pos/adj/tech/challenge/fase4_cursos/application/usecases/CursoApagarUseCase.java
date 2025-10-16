package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoApagarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoApagarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoQueryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoApagarUseCase implements CursoApagarInputPort {

    private final CursoQueryOutputPort cursoQueryOutputPort;

    private final CursoApagarOutputPort cursoApagarOutputPort;

    @Override
    public void apagarPorId(UUID id) {
        cursoQueryOutputPort.findById(id)
                .ifPresentOrElse(curso -> cursoApagarOutputPort.apagarPorId(curso.getId()), () -> {
                    throw new CursoNotFoundCustomException(id);
                });
    }
}
