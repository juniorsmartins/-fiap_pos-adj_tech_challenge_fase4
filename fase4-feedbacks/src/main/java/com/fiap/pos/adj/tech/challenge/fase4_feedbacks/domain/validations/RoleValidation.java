package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.validations;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.enums.RoleEnum;

public interface RoleValidation {

    Papel getOrCreateRole(RoleEnum role);
}
