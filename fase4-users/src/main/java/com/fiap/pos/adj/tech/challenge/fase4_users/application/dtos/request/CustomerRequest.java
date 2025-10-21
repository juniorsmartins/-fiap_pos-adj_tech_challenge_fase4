package com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotNull
        RoleEnum role
) {
}
