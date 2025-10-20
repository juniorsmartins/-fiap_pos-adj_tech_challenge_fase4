package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;

public interface EstudanteCriarInputPort {

    Estudante criar(CustomerRequest request);
}
