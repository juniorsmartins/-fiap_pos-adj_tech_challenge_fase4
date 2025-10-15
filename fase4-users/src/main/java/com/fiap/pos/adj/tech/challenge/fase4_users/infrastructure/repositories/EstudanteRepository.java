package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories;

import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.EstudanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EstudanteRepository extends JpaRepository<EstudanteEntity, UUID> {

    Optional<EstudanteEntity> findByUserEmail(String email);
}
