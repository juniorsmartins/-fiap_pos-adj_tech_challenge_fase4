package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.CustomerKafka;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.EstudantePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EstudanteSaveGateway implements EstudanteSaveOutputPort {

    private final EstudanteRepository estudanteRepository;

    private final EstudantePresenter estudantePresenter;

    @Transactional
    @Override
    public void save(CustomerKafka kafka) {
        var entity = estudantePresenter.toEntity(kafka);
        estudanteRepository.save(entity);
    }
}
