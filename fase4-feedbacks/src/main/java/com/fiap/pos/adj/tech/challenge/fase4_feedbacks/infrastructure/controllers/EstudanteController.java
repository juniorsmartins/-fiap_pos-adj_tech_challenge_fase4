package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.EstudanteNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.EstudanteRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteApagarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.EstudanteQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.EstudantePresenter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = {EstudanteController.URI_ESTUDANTES})
@RequiredArgsConstructor
public class EstudanteController {

    protected static final String URI_ESTUDANTES = "/api/v1/estudantes";

    private final EstudanteCriarInputPort estudanteCriarInputPort;

    private final EstudanteApagarInputPort estudanteApagarInputPort;

    private final EstudanteQueryOutputPort estudanteQueryOutputPort;

    private final EstudantePresenter estudantePresenter;

    @PostMapping
    public ResponseEntity<EstudanteResponse> criar(@RequestBody @Valid EstudanteRequest request) {
        var estudante = estudanteCriarInputPort.criar(request);
        var response = estudantePresenter.toResponse(estudante);

        return ResponseEntity
                .created(URI.create(URI_ESTUDANTES + "/" + response.id()))
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
    public ResponseEntity<EstudanteResponse> consultarPorId(@PathVariable(name = "id") final UUID id) {
        var response = estudanteQueryOutputPort.findById(id)
                .map(estudantePresenter::toResponse)
                .orElseThrow(() -> new EstudanteNotFoundCustomException(id));

        return ResponseEntity
                .ok()
                .body(response);
    }
}
