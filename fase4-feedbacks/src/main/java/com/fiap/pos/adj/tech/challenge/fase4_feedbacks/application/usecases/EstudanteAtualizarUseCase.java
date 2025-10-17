package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.EstudanteKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudanteAtualizarUseCase implements EstudanteAtualizarInputPort {

    private final EstudanteAtualizarOutputPort estudanteAtualizarOutputPort;

    @Override
    public void atualizar(EstudanteKafka kafka) {
        estudanteAtualizarOutputPort.atualizar(kafka);
    }
}
