package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.EstudanteNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.EstudantePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EstudanteAtualizarGateway implements EstudanteAtualizarOutputPort {

    private final EstudanteRepository estudanteRepository;

    private final EstudantePresenter estudantePresenter;

    @Transactional
    @Override
    public EstudanteResponse atualizar(Estudante estudante) {

        return estudanteRepository.findById(estudante.getId())
                .map(entity -> {
                    entity.setNome(estudante.getNome());
                    entity.getUser().setEmail(estudante.getUser().getEmail());
                    entity.getUser().setPassword(estudante.getUser().getPassword());
                    return entity;
                })
                .map(estudanteRepository::save)
                .map(estudantePresenter::toResponse)
                .orElseThrow(() -> new EstudanteNotFoundCustomException(estudante.getId()));
    }
}
