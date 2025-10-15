package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.EstudanteRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;

public interface EstudanteCriarInputPort {

    Estudante criar(EstudanteRequest request);
}
