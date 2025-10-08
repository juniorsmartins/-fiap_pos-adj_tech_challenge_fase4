package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.EstudantePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EstudanteQueryGateway implements EstudanteQueryOutputPort {

    private final EstudanteRepository estudanteRepository;

    private final EstudantePresenter estudantePresenter;

    @Transactional(readOnly = true)
    @Override
    public Optional<Estudante> findByEmail(String email) {
        return estudanteRepository.findByUserEmail(email)
                .map(estudantePresenter::toEstudante);
    }
}
