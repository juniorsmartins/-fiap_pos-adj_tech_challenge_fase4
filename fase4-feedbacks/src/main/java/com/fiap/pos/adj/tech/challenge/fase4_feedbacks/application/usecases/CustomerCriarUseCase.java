package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.usecases;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.CustomerCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.CustomerSaveOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.message.consumer.MessageCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCriarUseCase implements CustomerCriarInputPort {

    private final CustomerSaveOutputPort customerSaveOutputPort;

    @Override
    public void criar(MessageCustomer kafka) {
        customerSaveOutputPort.save(kafka);
    }
}
