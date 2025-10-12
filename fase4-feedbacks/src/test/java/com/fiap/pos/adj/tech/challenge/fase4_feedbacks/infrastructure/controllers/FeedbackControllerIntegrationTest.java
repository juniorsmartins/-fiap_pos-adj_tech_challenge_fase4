package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FeedbackControllerIntegrationTest {

    @Autowired
    private FeedbackController feedbackController;

    @Autowired
    private FeedbackRepository feedbackRepository;

}