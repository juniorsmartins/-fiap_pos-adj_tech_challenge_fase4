package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.UsuarioResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Usuario;

public interface UsuarioPresenter {

    UsuarioResponse toUsuarioResponse(Usuario usuario);
}
