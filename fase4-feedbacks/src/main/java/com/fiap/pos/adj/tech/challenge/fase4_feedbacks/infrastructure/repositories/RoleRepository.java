package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.repositories;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
}
