package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.UsuarioResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Usuario;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioPresenterImpl implements UsuarioPresenter {

    private final RolePresenter rolePresenter;

    @Override
    public UsuarioResponse toResponse(Usuario usuario) {
        var roleResponse = rolePresenter.toResponse(usuario.getRole());
        return new UsuarioResponse(usuario.getId(), usuario.getEmail(), usuario.getPassword(), roleResponse);
    }

    @Override
    public UserEntity toEntity(Usuario usuario) {
        var roleEntity = rolePresenter.toEntity(usuario.getRole());
        return new UserEntity(usuario.getId(), usuario.getEmail(), usuario.getPassword(), roleEntity);
    }

    @Override
    public Usuario toUsuario(UserEntity entity) {
        var papel = rolePresenter.toPapel(entity.getRole());
        return new Usuario(entity.getId(), entity.getEmail(), entity.getPassword(), papel);
    }
}
