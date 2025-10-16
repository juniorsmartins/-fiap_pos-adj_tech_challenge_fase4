package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.entities.Curso;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.validations.CursoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoCriarUseCase implements CursoCriarInputPort {

    private final CursoSaveOutputPort cursoSaveOutputPort;

    private final CursoValidation cursoValidation;

    @Override
    public CursoResponse criar(CursoRequest request) {

        cursoValidation.checkDuplicateNome(null, request.nome());

        var curso = new Curso(null, request.nome());
        return cursoSaveOutputPort.save(curso);
    }
}
