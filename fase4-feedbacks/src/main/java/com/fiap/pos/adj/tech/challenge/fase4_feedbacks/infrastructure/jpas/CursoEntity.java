package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cursos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public final class CursoEntity {

    @Id
    private UUID id;
}
