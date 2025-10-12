package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteApagarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.EstudantePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EstudanteApagarGateway implements EstudanteApagarOutputPort {

    private final EstudanteRepository estudanteRepository;

    private final EstudantePresenter estudantePresenter;

    @Transactional
    @Override
    public void apagar(Estudante estudante) {
        var entity = estudantePresenter.toEntity(estudante);
        estudanteRepository.delete(entity);
    }

    @Transactional
    @Override
    public void apagarPorId(UUID id) {
        estudanteRepository.deleteById(id);
    }
}
