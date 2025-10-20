package com.fiap.pos.adj.tech.challenge.fase4_users.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http404.CustomerNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.CustomerDesativarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerDesativarUseCase implements CustomerDesativarInputPort {

    private final CustomerQueryOutputPort customerQueryOutputPort;

    private final CustomerSaveOutputPort customerSaveOutputPort;

    @Override
    public void desativarPorId(UUID id) {
        customerQueryOutputPort.findByIdAndAtivoTrue(id)
                .ifPresentOrElse(customer -> {
                    var customerDesativado = new Customer(customer.getId(), customer.getNome(), false, customer.getUser());
                    customerSaveOutputPort.save(customerDesativado);
                }, () -> {
                    throw new CustomerNotFoundCustomException(id);
                });
    }
}
