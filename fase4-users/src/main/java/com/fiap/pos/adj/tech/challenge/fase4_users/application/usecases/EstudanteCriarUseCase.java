package com.fiap.pos.adj.tech.challenge.fase4_users.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.EstudanteCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Usuario;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations.CustomerValidation;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations.RoleValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudanteCriarUseCase implements EstudanteCriarInputPort {

    private final CustomerSaveOutputPort customerSaveOutputPort;

    private final CustomerValidation customerValidation;

    private final RoleValidation roleValidation;

    @Override
    public Customer criar(CustomerRequest request) {

        customerValidation.checkDuplicateEmail(null, request.email());

        var papel = roleValidation.getOrCreateRole(RoleEnum.ROLE_ESTUDANTE);
        var usuario = new Usuario(null, request.email(), request.password(), papel);
        var estudante = new Customer(null, request.nome(), true, usuario);
        return customerSaveOutputPort.save(estudante);
    }
}
