package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.configs.exceptions.http404.FeedbackNotFoundCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.FeedbackRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.FeedbackCriarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.FeedbackApagarInputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.FeedbackQueryOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.kafka.producer.Producer;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.presenters.FeedbackPresenter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = {FeedbackController.URI_FEEDBACKS})
@RequiredArgsConstructor
public class FeedbackController {

    protected static final String URI_FEEDBACKS = "/v1/feedbacks";

    private final FeedbackCriarInputPort feedbackCriarInputPort;

    private final FeedbackApagarInputPort feedbackApagarInputPort;

    private final FeedbackQueryOutputPort feedbackQueryOutputPort;

    private final FeedbackPresenter feedbackPresenter;

    private final Producer producer;

    @PostMapping
    public ResponseEntity<FeedbackResponse> criar(@RequestBody @Valid FeedbackRequest request) {
        var response = feedbackCriarInputPort.criar(request);

        producer.sendEventCreateFeedbacks(feedbackPresenter.toKafka(response));

        return ResponseEntity
                .created(URI.create(URI_FEEDBACKS + "/" + response.id()))
                .body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable(name = "id") final UUID id) {
        feedbackApagarInputPort.apagarPorId(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<FeedbackResponse> consultarPorId(@PathVariable(name = "id") final UUID id) {
        var response = feedbackQueryOutputPort.consultarPorId(id)
                .map(feedbackPresenter::toResponse)
                .orElseThrow(() -> new FeedbackNotFoundCustomException(id));

        return ResponseEntity
                .ok()
                .body(response);
    }
}
