package com.fiap.pos.adj.tech.challenge.fase4_users.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.CustomerAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Usuario;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations.CustomerValidation;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations.RoleValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerAtualizarUseCase implements CustomerAtualizarInputPort {

    private final CustomerAtualizarOutputPort customerAtualizarOutputPort;

    private final CustomerValidation customerValidation;

    private final RoleValidation roleValidation;

    @Override
    public CustomerResponse atualizarPorId(UUID id, CustomerRequest request) {

        customerValidation.checkDuplicateEmail(id, request.email());

        var papel = roleValidation.getOrCreateRole(request.role());
        var usuario = new Usuario(null, request.email(), request.password(), papel);
        var estudante = new Customer(id, request.nome(), true, usuario);

        return customerAtualizarOutputPort.atualizar(estudante);
    }
}
