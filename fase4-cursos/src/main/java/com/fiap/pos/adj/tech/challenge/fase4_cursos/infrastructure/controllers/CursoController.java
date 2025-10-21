package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoDesativarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.message.producer.Producer;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.presenters.CursoPresenter;
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

    public static final String URI_CURSOS = "/v1/cursos";

    private final CursoCriarInputPort cursoCriarInputPort;

    private final CursoAtualizarInputPort cursoAtualizarInputPort;

    private final CursoDesativarInputPort cursoDesativarInputPort;

    private final CursoQueryOutputPort cursoQueryOutputPort;

    private final CursoPresenter cursoPresenter;

    private final Producer producer;

    @PostMapping
    public ResponseEntity<CursoResponse> criar(@RequestBody @Valid CursoRequest request) {
        var response = cursoCriarInputPort.criar(request);
        var messageKafka = cursoPresenter.toMessage(response);
        producer.sendEventCreateCursos(messageKafka);

        return ResponseEntity
                .created(URI.create(URI_CURSOS + "/" + response.id()))
                .body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CursoResponse> atualizarPorId(@PathVariable(name = "id") final UUID id, @RequestBody @Valid CursoRequest request) {
        var response = cursoAtualizarInputPort.atualizarPorId(id, request);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> desativarPorId(@PathVariable(name = "id") final UUID id) {
        cursoDesativarInputPort.desativarPorId(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CursoResponse> consultarPorId(@PathVariable(name = "id") final UUID id) {
        var response = cursoQueryOutputPort.findByIdAndAtivoTrue(id)
                .map(cursoPresenter::toResponse)
                .orElseThrow(() -> new CursoNotFoundCustomException(id));
        return ResponseEntity
                .ok()
                .body(response);
    }
}
