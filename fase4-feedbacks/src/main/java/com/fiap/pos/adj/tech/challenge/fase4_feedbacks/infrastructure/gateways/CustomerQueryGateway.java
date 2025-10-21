package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CustomerQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.CustomerPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerQueryGateway implements CustomerQueryOutputPort {

    private final CustomerRepository customerRepository;

    private final CustomerPresenter customerPresenter;

    @Override
    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id)
                .map(customerPresenter::toModel);
    }
}
