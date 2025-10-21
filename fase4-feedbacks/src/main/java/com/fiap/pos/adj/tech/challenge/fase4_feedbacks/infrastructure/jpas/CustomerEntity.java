package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.infrastructure.jpas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "customers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public final class CustomerEntity {

    @Id
    private UUID id;
}
