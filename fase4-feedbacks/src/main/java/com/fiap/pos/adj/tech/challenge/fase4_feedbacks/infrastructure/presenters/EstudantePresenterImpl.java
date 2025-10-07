package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EstudantePresenterImpl implements EstudantePresenter {

    private final UsuarioPresenter usuarioPresenter;

    @Override
    public EstudanteResponse toEstudanteResponse(Estudante estudante) {
        var usuarioResponse = usuarioPresenter.toUsuarioResponse(estudante.getUser());
        return new EstudanteResponse(estudante.getId(), estudante.getNome(), usuarioResponse);
    }
}
