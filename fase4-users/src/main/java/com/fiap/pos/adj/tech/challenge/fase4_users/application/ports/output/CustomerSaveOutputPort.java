package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Customer;

public interface CustomerSaveOutputPort {

    Customer save(Customer customer);
}
