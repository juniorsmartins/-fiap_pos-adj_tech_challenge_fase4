package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.consumer.MessageCustomer;

public interface CustomerSaveOutputPort {

    void save(MessageCustomer kafka);
}
