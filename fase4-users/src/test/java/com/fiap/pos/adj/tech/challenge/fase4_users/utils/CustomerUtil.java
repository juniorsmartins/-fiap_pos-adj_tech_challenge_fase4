package com.fiap.pos.adj.tech.challenge.fase4_users.utils;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;

public final class CustomerUtil {

    public static CustomerRequest buildRequest(String nome, String email, String password) {
        return new CustomerRequest(nome, email, password);
    }
}
