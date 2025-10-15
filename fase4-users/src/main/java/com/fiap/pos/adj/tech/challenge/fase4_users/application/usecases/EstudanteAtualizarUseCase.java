package com.fiap.pos.adj.tech.challenge.fase4_users.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.EstudanteRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.EstudanteAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.EstudanteAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Usuario;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations.EstudanteValidation;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations.RoleValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstudanteAtualizarUseCase implements EstudanteAtualizarInputPort {

    private final EstudanteAtualizarOutputPort estudanteAtualizarOutputPort;

    private final EstudanteValidation estudanteValidation;

    private final RoleValidation roleValidation;

    @Override
    public EstudanteResponse atualizarPorId(UUID id, EstudanteRequest request) {

        estudanteValidation.checkDuplicateEmail(id, request.email());

        var papel = roleValidation.getOrCreateRole(RoleEnum.ROLE_ESTUDANTE);
        var usuario = new Usuario(null, request.email(), request.password(), papel);
        var estudante = new Estudante(id, request.nome(), usuario);

        return estudanteAtualizarOutputPort.atualizar(estudante);
    }
}
