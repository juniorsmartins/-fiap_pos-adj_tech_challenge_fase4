package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.EstudanteQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.EstudantePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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

    @Transactional
    @Override
    public Optional<Estudante> findById(UUID id) {
        return estudanteRepository.findById(id)
                .map(estudantePresenter::toEstudante);
    }
}
