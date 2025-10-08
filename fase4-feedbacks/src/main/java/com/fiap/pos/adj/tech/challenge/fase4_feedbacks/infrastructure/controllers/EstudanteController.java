package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.EstudanteRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.EstudanteResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.EstudanteCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.EstudantePresenter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = {EstudanteController.URI_ESTUDANTES})
@RequiredArgsConstructor
public class EstudanteController {

    protected static final String URI_ESTUDANTES = "/api/v1/estudantes";

    private final EstudanteCriarInputPort estudanteCriarInputPort;

    private final EstudantePresenter estudantePresenter;

    @PostMapping
    public ResponseEntity<EstudanteResponse> criar(@RequestBody @Valid EstudanteRequest request) {
        var estudante = estudanteCriarInputPort.criar(request);
        var response = estudantePresenter.toResponse(estudante);
        return ResponseEntity
                .created(URI.create(URI_ESTUDANTES + "/" + response.id()))
                .body(response);
    }
}
