package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.message.producer.MessageCustomer;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.CustomerEntity;

public interface CustomerPresenter {

    CustomerResponse toResponse(Customer customer);

    CustomerResponse toResponse(CustomerEntity entity);

    CustomerEntity toEntity(Customer customer);

    Customer toCustomer(CustomerEntity entity);

    MessageCustomer toMessage(CustomerResponse response);
}
