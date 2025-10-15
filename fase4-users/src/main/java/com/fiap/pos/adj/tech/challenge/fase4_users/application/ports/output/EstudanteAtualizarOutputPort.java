package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;

public interface EstudanteAtualizarOutputPort {

    EstudanteResponse atualizar(Estudante estudante);
}
