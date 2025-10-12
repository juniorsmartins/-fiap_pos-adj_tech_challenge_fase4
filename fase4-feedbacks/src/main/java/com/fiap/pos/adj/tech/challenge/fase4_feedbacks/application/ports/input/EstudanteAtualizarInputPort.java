package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.EstudanteRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;

import java.util.UUID;

public interface EstudanteAtualizarInputPort {

    EstudanteResponse atualizarPorId(UUID id, EstudanteRequest request);
}
