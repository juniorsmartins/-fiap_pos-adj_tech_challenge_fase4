package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer.CustomerKafka;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class CustomerPresenterImpl implements CustomerPresenter {

    private final UsuarioPresenter usuarioPresenter;

    @Override
    public CustomerResponse toResponse(Customer customer) {
        var usuarioResponse = usuarioPresenter.toResponse(customer.getUser());
        return new CustomerResponse(customer.getId(), customer.getNome(), usuarioResponse);
    }

    @Override
    public CustomerResponse toResponse(CustomerEntity entity) {
        var usuarioResponse = usuarioPresenter.toResponse(entity.getUser());
        return new CustomerResponse(entity.getId(), entity.getNome(), usuarioResponse);
    }

    @Override
    public CustomerEntity toEntity(Customer customer) {
        var userEntity = usuarioPresenter.toEntity(customer.getUser());
        return new CustomerEntity(customer.getId(), customer.getNome(), customer.isAtivo(), userEntity);
    }

    @Override
    public Customer toCustomer(CustomerEntity entity) {
        var usuario = usuarioPresenter.toUsuario(entity.getUser());
        return new Customer(entity.getId(), entity.getNome(), entity.isAtivo(), usuario);
    }

    @Override
    public CustomerKafka toKafka(CustomerResponse response) {
        return new CustomerKafka(response.id(), response.nome(), response.usuario().email());
    }
}
