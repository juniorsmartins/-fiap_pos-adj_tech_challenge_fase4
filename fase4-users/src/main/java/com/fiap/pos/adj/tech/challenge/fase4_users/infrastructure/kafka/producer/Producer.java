package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer;

public interface Producer {

    void sendEventCreateCustomers(MessageCustomer kafka);
}
