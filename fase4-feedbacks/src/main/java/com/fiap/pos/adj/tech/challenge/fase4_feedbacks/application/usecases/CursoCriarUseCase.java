package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.CursoCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CursoSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.MessageCurso;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoCriarUseCase implements CursoCriarInputPort {

    private final CursoSaveOutputPort cursoSaveOutputPort;

    @Override
    public void criar(MessageCurso kafka) {
        cursoSaveOutputPort.save(kafka);
    }
}
