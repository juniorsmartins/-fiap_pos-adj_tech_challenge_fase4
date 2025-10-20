package com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.repositories;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.infrastructure.jpas.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CursoRepository extends JpaRepository<CursoEntity, UUID> {

    Optional<CursoEntity> findByIdAndAtivoTrue(UUID id);

    Optional<CursoEntity> findByNome(String nome);
}
