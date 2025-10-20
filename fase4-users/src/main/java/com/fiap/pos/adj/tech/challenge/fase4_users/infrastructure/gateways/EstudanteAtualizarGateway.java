package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http404.CustomerNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.EstudanteAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.EstudantePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EstudanteAtualizarGateway implements EstudanteAtualizarOutputPort {

    private final CustomerRepository customerRepository;

    private final EstudantePresenter estudantePresenter;

    @Transactional
    @Override
    public CustomerResponse atualizar(Estudante estudante) {

        return customerRepository.findById(estudante.getId())
                .map(entity -> {
                    entity.setNome(estudante.getNome());
                    entity.getUser().setEmail(estudante.getUser().getEmail());
                    entity.getUser().setPassword(estudante.getUser().getPassword());
                    return entity;
                })
                .map(customerRepository::save)
                .map(estudantePresenter::toResponse)
                .orElseThrow(() -> new CustomerNotFoundCustomException(estudante.getId()));
    }
}
