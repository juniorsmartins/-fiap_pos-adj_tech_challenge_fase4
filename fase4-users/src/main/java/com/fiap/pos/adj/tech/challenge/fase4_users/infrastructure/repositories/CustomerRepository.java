package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.repositories;

import com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByUserEmail(String email);
}
