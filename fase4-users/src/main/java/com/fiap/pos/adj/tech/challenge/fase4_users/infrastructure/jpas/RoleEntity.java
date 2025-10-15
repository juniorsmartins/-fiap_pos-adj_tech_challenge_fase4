package com.fiap.pos.adj.tech.challenge.fase4_users.infrastructure.jpas;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public final class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false, unique = true)
    private RoleEnum nome;
}
