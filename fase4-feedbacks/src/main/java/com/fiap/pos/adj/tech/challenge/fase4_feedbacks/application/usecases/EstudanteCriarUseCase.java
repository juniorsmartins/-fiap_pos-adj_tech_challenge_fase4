package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.EstudanteRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Usuario;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.validations.RoleValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudanteCriarUseCase implements EstudanteCriarInputPort {

    private final EstudanteSaveOutputPort estudanteSaveOutputPort;

    private final RoleValidation roleValidation;

    @Override
    public Estudante criar(EstudanteRequest request) {
        var papel = roleValidation.getOrCreateRole(RoleEnum.ROLE_ESTUDANTE);
        var usuario = new Usuario(null, request.email(), request.password(), papel);
        var estudante = new Estudante(null, request.nome(), usuario);
        return estudanteSaveOutputPort.save(estudante);
    }
}
