package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CustomerEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.MessageCustomer;

public interface CustomerPresenter {

    CustomerEntity toEntity(MessageCustomer kafka);

    CustomerEntity toEntity(Customer model);

    Customer toModel(CustomerEntity entity);

    CustomerResponse toResponse(Customer model);

    CustomerResponse toResponse(CustomerEntity entity);
}
