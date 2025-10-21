package com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;

public record CustomerRequest(

        String nome,

        String email,

        String password,

        RoleEnum role
) {
}
