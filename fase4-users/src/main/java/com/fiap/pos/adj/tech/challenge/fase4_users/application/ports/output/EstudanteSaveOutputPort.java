package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Estudante;

public interface EstudanteSaveOutputPort {

    Estudante save(Estudante estudante);
}
