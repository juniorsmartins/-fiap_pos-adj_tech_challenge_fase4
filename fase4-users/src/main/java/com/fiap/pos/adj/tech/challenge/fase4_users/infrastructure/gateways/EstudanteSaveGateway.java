package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.EstudanteSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.EstudantePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EstudanteSaveGateway implements EstudanteSaveOutputPort {

    private final CustomerRepository customerRepository;

    private final EstudantePresenter estudantePresenter;

    @Transactional
    @Override
    public Estudante save(Estudante estudante) {
        var entity = estudantePresenter.toEntity(estudante);
        var entitySave = customerRepository.save(entity);
        return estudantePresenter.toEstudante(entitySave);
    }
}
