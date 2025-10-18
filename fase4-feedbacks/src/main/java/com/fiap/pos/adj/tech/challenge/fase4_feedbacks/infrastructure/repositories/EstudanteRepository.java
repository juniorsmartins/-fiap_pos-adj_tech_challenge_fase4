package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.EstudanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstudanteRepository extends JpaRepository<EstudanteEntity, UUID> {

}
