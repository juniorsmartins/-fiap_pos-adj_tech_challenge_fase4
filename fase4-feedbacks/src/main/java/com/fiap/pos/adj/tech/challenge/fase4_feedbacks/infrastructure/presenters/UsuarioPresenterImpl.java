package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.UsuarioResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioPresenterImpl implements UsuarioPresenter {

    private final RolePresenter rolePresenter;

    @Override
    public UsuarioResponse toUsuarioResponse(Usuario usuario) {
        var roleResponse = rolePresenter.toRoleResponse(usuario.getRole());
        return new UsuarioResponse(usuario.getId(), usuario.getEmail(), usuario.getPassword(), roleResponse);
    }
}
