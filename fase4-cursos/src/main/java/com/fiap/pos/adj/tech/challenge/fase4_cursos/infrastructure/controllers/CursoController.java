package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http404.CursoNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.request.CursoRequest;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.dtos.response.CursoResponse;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoApagarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoAtualizarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.input.CursoCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.ports.output.CursoQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.kafka.producer.KafkaProducer;
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

    private final CursoApagarInputPort cursoApagarInputPort;

    private final CursoQueryOutputPort cursoQueryOutputPort;

    private final CursoPresenter cursoPresenter;

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<CursoResponse> criar(@RequestBody @Valid CursoRequest request) {

        var response = cursoCriarInputPort.criar(request);
        var messageKafka = cursoPresenter.toKafka(response);
        kafkaProducer.sendEventCreateCursos(messageKafka);

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
    public ResponseEntity<Void> apagarPorId(@PathVariable(name = "id") final UUID id) {
        cursoApagarInputPort.apagarPorId(id);
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
