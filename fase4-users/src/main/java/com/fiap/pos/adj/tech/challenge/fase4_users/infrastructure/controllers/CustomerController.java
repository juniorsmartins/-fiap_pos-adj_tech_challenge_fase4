package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http404.CustomerNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.CustomerDesativarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.CustomerAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.CustomerCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer.KafkaProducer;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.CustomerPresenter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = {CustomerController.URI_CUSTOMER})
@RequiredArgsConstructor
public class CustomerController {

    protected static final String URI_CUSTOMER = "/v1/customers";

    private final CustomerCriarInputPort customerCriarInputPort;

    private final CustomerAtualizarInputPort customerAtualizarInputPort;

    private final CustomerDesativarInputPort customerDesativarInputPort;

    private final CustomerQueryOutputPort customerQueryOutputPort;

    private final CustomerPresenter customerPresenter;

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<CustomerResponse> criar(@RequestBody @Valid CustomerRequest request) {
        var customer = customerCriarInputPort.criar(request);
        var response = customerPresenter.toResponse(customer);

        kafkaProducer.enviarEventoUsers(customerPresenter.toKafka(response));

        return ResponseEntity
                .created(URI.create(URI_CUSTOMER + "/" + response.id()))
                .body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> atualizarPorId(@PathVariable(name = "id") final UUID id, @RequestBody @Valid CustomerRequest request) {
        var response = customerAtualizarInputPort.atualizarPorId(id, request);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> desativarPorId(@PathVariable(name = "id") final UUID id) {
        customerDesativarInputPort.desativarPorId(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<CustomerResponse> consultarPorId(@PathVariable(name = "id") final UUID id) {
        var response = customerQueryOutputPort.findByIdAndAtivoTrue(id)
                .map(customerPresenter::toResponse)
                .orElseThrow(() -> new CustomerNotFoundCustomException(id));

        return ResponseEntity
                .ok()
                .body(response);
    }
}
