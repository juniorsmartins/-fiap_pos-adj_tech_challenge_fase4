package com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.input;

import java.util.UUID;

public interface CustomerDesativarInputPort {

    void desativarPorId(UUID id);
}
