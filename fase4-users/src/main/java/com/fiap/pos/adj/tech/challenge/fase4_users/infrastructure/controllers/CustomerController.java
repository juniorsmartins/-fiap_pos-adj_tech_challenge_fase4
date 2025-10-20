package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http404.CustomerNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.request.CustomerRequest;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.dtos.response.CustomerResponse;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.EstudanteApagarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.EstudanteAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input.EstudanteCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.EstudanteQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.kafka.producer.KafkaProducer;
import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.presenters.EstudantePresenter;
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

    private final EstudanteCriarInputPort estudanteCriarInputPort;

    private final EstudanteAtualizarInputPort estudanteAtualizarInputPort;

    private final EstudanteApagarInputPort estudanteApagarInputPort;

    private final EstudanteQueryOutputPort estudanteQueryOutputPort;

    private final EstudantePresenter estudantePresenter;

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<CustomerResponse> criar(@RequestBody @Valid CustomerRequest request) {
        var customer = estudanteCriarInputPort.criar(request);
        var response = estudantePresenter.toResponse(customer);

        kafkaProducer.enviarEventoUsers(estudantePresenter.toKafka(response));

        return ResponseEntity
                .created(URI.create(URI_CUSTOMER + "/" + response.id()))
                .body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> atualizarPorId(@PathVariable(name = "id") final UUID id, @RequestBody @Valid CustomerRequest request) {
        var response = estudanteAtualizarInputPort.atualizarPorId(id, request);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> apagarPorId(@PathVariable(name = "id") final UUID id) {
        estudanteApagarInputPort.apagarPorId(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<CustomerResponse> consultarPorId(@PathVariable(name = "id") final UUID id) {
        var response = estudanteQueryOutputPort.findById(id)
                .map(estudantePresenter::toResponse)
                .orElseThrow(() -> new CustomerNotFoundCustomException(id));

        return ResponseEntity
                .ok()
                .body(response);
    }
}
