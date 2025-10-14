package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.request.FeedbackRequest;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.dtos.response.FeedbackResponse;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.input.FeedbackCriarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = {FeedbackController.URI_FEEDBACKS})
@RequiredArgsConstructor
public class FeedbackController {

    protected static final String URI_FEEDBACKS = "/api/v1/feedbacks";

    private final FeedbackCriarInputPort feedbackCriarInputPort;

    @PostMapping
    public ResponseEntity<FeedbackResponse> criar(@RequestBody @Valid FeedbackRequest request) {
        var response = feedbackCriarInputPort.criar(request);

        return ResponseEntity
                .created(URI.create(URI_FEEDBACKS + "/" + response.id()))
                .body(response);
    }
}
