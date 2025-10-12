package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.CursoApagarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.CursoCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.CursoPresenter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = {CursoController.URI_CURSOS})
@RequiredArgsConstructor
public class CursoController {

    public static final String URI_CURSOS = "/api/v1/cursos";

    private final CursoCriarInputPort cursoCriarInputPort;

    private final CursoApagarInputPort cursoApagarInputPort;

    private final CursoPresenter cursoPresenter;

    @PostMapping
    public ResponseEntity<CursoResponse> criar(@RequestBody @Valid CursoRequest request) {
        var response = cursoCriarInputPort.criar(request);

        return ResponseEntity
                .created(URI.create(URI_CURSOS + "/" + response.id()))
                .body(response);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> apagarPorId(@PathVariable(name = "id") final UUID id) {
        cursoApagarInputPort.apagarPorId(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
