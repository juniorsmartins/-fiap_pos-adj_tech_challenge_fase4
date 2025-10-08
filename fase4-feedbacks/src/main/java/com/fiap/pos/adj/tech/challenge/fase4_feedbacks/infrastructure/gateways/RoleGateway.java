package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.RoleOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.RolePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleGateway implements RoleOutputPort {

    private final RoleRepository roleRepository;

    private final RolePresenter rolePresenter;

    @Override
    public Optional<Papel> findByNome(RoleEnum nome) {
        return roleRepository.findByNome(nome)
                .map(rolePresenter::toPapel);
    }

    @Override
    public Papel save(Papel papel) {
        var entity = rolePresenter.toEntity(papel);
        var entitySave = roleRepository.save(entity);
        return rolePresenter.toPapel(entitySave);
    }
}
