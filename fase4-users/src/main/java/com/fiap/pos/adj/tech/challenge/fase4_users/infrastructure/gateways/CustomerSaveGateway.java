package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.CustomerPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CustomerSaveGateway implements CustomerSaveOutputPort {

    private final CustomerRepository customerRepository;

    private final CustomerPresenter customerPresenter;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        var entity = customerPresenter.toEntity(customer);
        var entitySave = customerRepository.save(entity);
        return customerPresenter.toEstudante(entitySave);
    }
}
