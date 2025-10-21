package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.message.producer;

public interface Producer {

    void sendEventCreateCustomers(MessageCustomer kafka);
}
