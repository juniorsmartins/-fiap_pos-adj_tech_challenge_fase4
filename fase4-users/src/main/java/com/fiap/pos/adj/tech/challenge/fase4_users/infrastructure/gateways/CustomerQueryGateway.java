package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.CustomerPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerQueryGateway implements CustomerQueryOutputPort {

    private final CustomerRepository customerRepository;

    private final CustomerPresenter customerPresenter;

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByUserEmail(email)
                .map(customerPresenter::toEstudante);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findByIdAndAtivoTrue(UUID id) {
        return customerRepository.findByIdAndAtivoTrue(id)
                .map(customerPresenter::toEstudante);
    }
}
