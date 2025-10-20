package com.fiap.pos.adj.tech.challenge.fase4_users.utils;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.CustomerEntity;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.UserEntity;

import java.util.UUID;

public final class CustomerUtil {

    public static CustomerRequest buildRequest(String nome, String email, String password) {
        return new CustomerRequest(nome, email, password);
    }

    public static CustomerEntity buildEntity(UUID id, String nome, UserEntity userEntity) {
        return new CustomerEntity(id, nome, userEntity);
    }
}
