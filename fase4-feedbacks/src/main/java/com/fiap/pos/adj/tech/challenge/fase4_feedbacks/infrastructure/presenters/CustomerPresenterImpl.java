package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Customer;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.CustomerEntity;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.consumer.MessageCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class CustomerPresenterImpl implements CustomerPresenter {

    @Override
    public CustomerEntity toEntity(MessageCustomer kafka) {
        return new CustomerEntity(kafka.id());
    }

    @Override
    public CustomerEntity toEntity(Customer model) {
        return new CustomerEntity(model.id());
    }

    @Override
    public Customer toModel(CustomerEntity entity) {
        return new Customer(entity.getId());
    }

    @Override
    public CustomerResponse toResponse(Customer model) {
        return new CustomerResponse(model.id());
    }

    @Override
    public CustomerResponse toResponse(CustomerEntity entity) {
        return new CustomerResponse(entity.getId());
    }
}
