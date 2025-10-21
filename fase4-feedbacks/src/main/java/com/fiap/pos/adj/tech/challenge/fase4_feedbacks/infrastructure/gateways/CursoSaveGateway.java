package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CursoSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.MessageCurso;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.CursoPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CursoSaveGateway implements CursoSaveOutputPort {

    private final CursoRepository cursoRepository;

    private final CursoPresenter cursoPresenter;

    @Transactional
    @Override
    public void save(MessageCurso kafka) {
        var entity = cursoPresenter.toEntity(kafka);
        cursoRepository.save(entity);
    }
}
