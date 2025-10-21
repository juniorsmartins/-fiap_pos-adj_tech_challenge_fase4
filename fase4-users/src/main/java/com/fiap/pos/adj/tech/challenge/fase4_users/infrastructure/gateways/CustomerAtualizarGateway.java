package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http404.CustomerNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerAtualizarOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.CustomerPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.RolePresenter;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CustomerAtualizarGateway implements CustomerAtualizarOutputPort {

    private final CustomerRepository customerRepository;

    private final CustomerPresenter customerPresenter;

    private final RolePresenter rolePresenter;

    @Transactional
    @Override
    public CustomerResponse atualizar(Customer customer) {

        return customerRepository.findById(customer.getId())
                .map(entity -> {
                    entity.setNome(customer.getNome());
                    entity.getUser().setEmail(customer.getUser().getEmail());
                    entity.getUser().setPassword(customer.getUser().getPassword());
                    var roleEntiy = rolePresenter.toEntity(customer.getUser().getRole());
                    entity.getUser().setRole(roleEntiy);
                    return entity;
                })
                .map(customerRepository::save)
                .map(customerPresenter::toResponse)
                .orElseThrow(() -> new CustomerNotFoundCustomException(customer.getId()));
    }
}
