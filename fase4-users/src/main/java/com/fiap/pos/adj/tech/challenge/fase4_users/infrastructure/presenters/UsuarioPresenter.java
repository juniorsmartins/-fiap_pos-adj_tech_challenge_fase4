package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.UsuarioResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Usuario;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.UserEntity;

public interface UsuarioPresenter {

    UsuarioResponse toResponse(Usuario usuario);

    UsuarioResponse toResponse(UserEntity user);

    UserEntity toEntity(Usuario usuario);

    Usuario toUsuario(UserEntity entity);
}
