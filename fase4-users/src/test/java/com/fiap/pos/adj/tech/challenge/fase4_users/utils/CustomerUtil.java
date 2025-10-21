package com.fiap.pos.adj.tech.challenge.fase4_users.utils;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;

public final class CustomerUtil {

    public static CustomerRequest buildRequest(String nome, String email, String password, RoleEnum role) {
        return new CustomerRequest(nome, email, password, role);
    }
}
