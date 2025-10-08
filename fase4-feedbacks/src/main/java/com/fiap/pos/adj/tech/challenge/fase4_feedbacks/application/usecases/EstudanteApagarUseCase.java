package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.EstudanteNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteApagarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteApagarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteQueryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstudanteApagarUseCase implements EstudanteApagarInputPort {

    private final EstudanteQueryOutputPort estudanteQueryOutputPort;

    private final EstudanteApagarOutputPort estudanteApagarOutputPort;

    @Override
    public void apagarPorId(UUID id) {
        estudanteQueryOutputPort.findById(id)
                .ifPresentOrElse(estudanteApagarOutputPort::apagar, () -> {
                    throw new EstudanteNotFoundCustomException(id);
                });
    }
}
