package com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations;

import com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_users.domain.enums.RoleEnum;

public interface RoleValidation {

    Papel getOrCreateRole(RoleEnum role);
}
