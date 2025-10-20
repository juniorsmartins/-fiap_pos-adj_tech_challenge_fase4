package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;

import java.util.UUID;

public interface CustomerAtualizarInputPort {

    CustomerResponse atualizarPorId(UUID id, CustomerRequest request);
}
