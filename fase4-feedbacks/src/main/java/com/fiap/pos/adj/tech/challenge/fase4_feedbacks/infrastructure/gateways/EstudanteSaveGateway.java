package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.EstudantePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EstudanteSaveGateway implements EstudanteSaveOutputPort {

    private final EstudanteRepository estudanteRepository;

    private final EstudantePresenter estudantePresenter;

    @Override
    public Estudante save(Estudante estudante) {
        var entity = estudantePresenter.toEntity(estudante);
        var entitySave = estudanteRepository.save(entity);
        return estudantePresenter.toEstudante(entitySave);
    }
}
