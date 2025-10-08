package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;

public interface EstudanteAtualizarOutputPort {

    EstudanteResponse atualizar(Estudante estudante);
}
