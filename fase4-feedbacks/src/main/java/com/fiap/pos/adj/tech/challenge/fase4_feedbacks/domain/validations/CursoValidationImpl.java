package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.validations;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http409.NomeDuplicatedCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CursoQueryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CursoValidationImpl implements CursoValidation {

    private final CursoQueryOutputPort cursoQueryOutputPort;

    @Override
    public void checkDuplicateNome(UUID id, String nome) {
        cursoQueryOutputPort.findByNome(nome)
                .ifPresent(curso -> {
                    if (id == null || !id.equals(curso.getId())) {
                        throw new NomeDuplicatedCustomException(nome);
                    }
                });
    }
}
