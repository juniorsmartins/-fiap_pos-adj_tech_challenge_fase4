package com.fiap.pos.adj.tech.challenge.fase4_users.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.EstudanteCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.EstudanteSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Usuario;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations.EstudanteValidation;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations.RoleValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudanteCriarUseCase implements EstudanteCriarInputPort {

    private final EstudanteSaveOutputPort estudanteSaveOutputPort;

    private final EstudanteValidation estudanteValidation;

    private final RoleValidation roleValidation;

    @Override
    public Estudante criar(CustomerRequest request) {

        estudanteValidation.checkDuplicateEmail(null, request.email());

        var papel = roleValidation.getOrCreateRole(RoleEnum.ROLE_ESTUDANTE);
        var usuario = new Usuario(null, request.email(), request.password(), papel);
        var estudante = new Estudante(null, request.nome(), usuario);
        return estudanteSaveOutputPort.save(estudante);
    }
}
