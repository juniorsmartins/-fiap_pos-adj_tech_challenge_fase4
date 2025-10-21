package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.gateways;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CustomerSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.consumer.MessageCustomer;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.CustomerPresenter;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories.CustomerRepository;
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
    public void save(MessageCustomer kafka) {
        var entity = customerPresenter.toEntity(kafka);
        customerRepository.save(entity);
    }
}
