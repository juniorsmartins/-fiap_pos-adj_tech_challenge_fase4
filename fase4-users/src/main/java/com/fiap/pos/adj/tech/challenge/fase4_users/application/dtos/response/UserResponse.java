package com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"id", "email", "password", "role"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(

        UUID id,

        String email,

        String password,

        RoleResponse role
) {
}
