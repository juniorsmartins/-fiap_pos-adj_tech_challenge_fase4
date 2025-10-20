package com.fiap.pos.adj.tech.challenge.fase4_cursos.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoDesativarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models.Curso;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoDesativarUseCase implements CursoDesativarInputPort {

    private final CursoQueryOutputPort cursoQueryOutputPort;

    private final CursoSaveOutputPort cursoSaveOutputPort;

    @Override
    public void desativarPorId(UUID id) {
        cursoQueryOutputPort.findByIdAndAtivoTrue(id)
                .ifPresentOrElse(curso -> {
                            var cursoDesativado = new Curso(curso.getId(), curso.getNome(), false);
                            cursoSaveOutputPort.save(cursoDesativado);
                        }, () -> {
                            throw new CursoNotFoundCustomException(id);
                        }
                );
    }
}
