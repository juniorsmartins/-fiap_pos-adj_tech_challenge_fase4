package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.MessageCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudanteCriarUseCase implements EstudanteCriarInputPort {

    private final EstudanteSaveOutputPort estudanteSaveOutputPort;

    @Override
    public void criar(MessageCustomer kafka) {
        estudanteSaveOutputPort.save(kafka);
    }
}
