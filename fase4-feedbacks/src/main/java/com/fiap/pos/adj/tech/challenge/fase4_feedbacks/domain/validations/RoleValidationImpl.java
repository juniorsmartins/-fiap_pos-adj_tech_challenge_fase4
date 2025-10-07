package com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.validations;

import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.application.ports.output.RoleOutputPort;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.entities.Papel;
import com.fiap.pos.adj.tech.challenge.fase4_feedbacks.domain.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleValidationImpl implements RoleValidation {

    private final RoleOutputPort roleOutputPort;

    @Override
    public Papel getOrCreateRole(RoleEnum role) {
      var papel = roleOutputPort.findByNome(role);
      return papel.orElseGet(() -> roleOutputPort.save(new Papel(null, role)));
    }
}
