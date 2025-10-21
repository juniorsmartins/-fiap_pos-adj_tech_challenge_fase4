package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "feedbacks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public final class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nota", nullable = false)
    private int nota;

    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoEntity curso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudante_id", nullable = false)
    private CustomerEntity estudante;
}
