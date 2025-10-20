package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerQueryOutputPort {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByIdAndAtivoTrue(UUID id);
}
