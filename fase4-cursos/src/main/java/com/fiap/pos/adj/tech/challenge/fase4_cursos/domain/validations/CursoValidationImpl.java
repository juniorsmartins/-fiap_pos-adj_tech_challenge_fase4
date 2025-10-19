package com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.validations;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http409.NomeDuplicatedCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoQueryOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CursoValidationImpl implements CursoValidation {

    private final CursoQueryOutputPort cursoQueryOutputPort;

    @Override
    public void checkDuplicateNome(UUID id, String nome) {
        log.info("\n\n\n checkDuplicateNome acionado \n\n\n");
        cursoQueryOutputPort.findByNome(nome)
                .ifPresent(curso -> {
                    if (id == null || !id.equals(curso.getId())) {
                        log.error("\n\n\n checkDuplicateNome gera exceção \n\n\n");
                        throw new NomeDuplicatedCustomException(nome);
                    }
                });
    }
}
