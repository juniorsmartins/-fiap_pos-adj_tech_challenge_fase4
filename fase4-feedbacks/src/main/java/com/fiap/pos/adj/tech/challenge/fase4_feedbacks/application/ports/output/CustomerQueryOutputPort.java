package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerQueryOutputPort {

    Optional<Customer> findById(UUID id);
}
