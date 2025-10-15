package com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;

import java.util.UUID;

@JsonPropertyOrder({"id", "nome"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoleResponse(

        UUID id,

        RoleEnum nome
) {
}
