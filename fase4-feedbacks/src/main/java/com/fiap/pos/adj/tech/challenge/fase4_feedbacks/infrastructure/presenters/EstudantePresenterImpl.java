package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EstudantePresenterImpl implements EstudantePresenter {

    private final UsuarioPresenter usuarioPresenter;

    @Override
    public EstudanteResponse toResponse(Estudante estudante) {
        var usuarioResponse = usuarioPresenter.toResponse(estudante.getUser());
        return new EstudanteResponse(estudante.getId(), estudante.getNome(), usuarioResponse);
    }

    @Override
    public EstudanteEntity toEntity(Estudante estudante) {
        var userEntity = usuarioPresenter.toEntity(estudante.getUser());
        return new EstudanteEntity(estudante.getId(), estudante.getNome(), userEntity);
    }

    @Override
    public Estudante toEstudante(EstudanteEntity entity) {
        var usuario = usuarioPresenter.toUsuario(entity.getUser());
        return new Estudante(entity.getId(), entity.getNome(), usuario);
    }
}
