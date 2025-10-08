package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.validations;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http409.EmailConflictRulesCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteQueryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EstudanteValidationImpl implements EstudanteValidation {

    private final EstudanteQueryOutputPort estudanteQueryOutputPort;

    @Override
    public void checkDuplicateEmail(UUID customerId, String email) {
        estudanteQueryOutputPort.findByEmail(email)
                .ifPresent(estudante -> {
                    if (customerId == null || !customerId.equals(estudante.getId())) {
                        throw new EmailConflictRulesCustomException(email);
                    }
                });
    }
}
