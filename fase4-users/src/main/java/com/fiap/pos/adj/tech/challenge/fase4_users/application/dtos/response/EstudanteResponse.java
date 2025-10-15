package com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"id", "nome", "usuario"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EstudanteResponse(

        UUID id,

        String nome,

        UsuarioResponse usuario
) {
}
