package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer.EstudanteKafka;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EstudantePresenterImpl implements EstudantePresenter {

    private final UsuarioPresenter usuarioPresenter;

    @Override
    public CustomerResponse toResponse(Estudante estudante) {
        var usuarioResponse = usuarioPresenter.toResponse(estudante.getUser());
        return new CustomerResponse(estudante.getId(), estudante.getNome(), usuarioResponse);
    }

    @Override
    public CustomerResponse toResponse(CustomerEntity entity) {
        var usuarioResponse = usuarioPresenter.toResponse(entity.getUser());
        return new CustomerResponse(entity.getId(), entity.getNome(), usuarioResponse);
    }

    @Override
    public CustomerEntity toEntity(Estudante estudante) {
        var userEntity = usuarioPresenter.toEntity(estudante.getUser());
        return new CustomerEntity(estudante.getId(), estudante.getNome(), userEntity);
    }

    @Override
    public Estudante toEstudante(CustomerEntity entity) {
        var usuario = usuarioPresenter.toUsuario(entity.getUser());
        return new Estudante(entity.getId(), entity.getNome(), usuario);
    }

    @Override
    public EstudanteKafka toKafka(CustomerResponse response) {
        return new EstudanteKafka(response.id(), response.nome(), response.usuario().email());
    }
}
