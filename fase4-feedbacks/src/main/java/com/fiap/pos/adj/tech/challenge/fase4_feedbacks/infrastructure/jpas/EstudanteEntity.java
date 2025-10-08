package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "estudantes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public final class EstudanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;
}
