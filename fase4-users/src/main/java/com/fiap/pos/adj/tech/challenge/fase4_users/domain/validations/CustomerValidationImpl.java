package com.fiap.pos.adj.tech.challenge.fase4_users.domain.validations;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http409.EmailConflictRulesCustomException;
import com.fiap.pos.adj.tech.challenge.fase4_users.application.ports.output.CustomerQueryOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerValidationImpl implements CustomerValidation {

    private final CustomerQueryOutputPort customerQueryOutputPort;

    @Override
    public void checkDuplicateEmail(UUID customerId, String email) {
        customerQueryOutputPort.findByEmail(email)
                .ifPresent(customer -> {
                    if (customerId == null || !customerId.equals(customer.getId())) {
                        throw new EmailConflictRulesCustomException(email);
                    }
                });
    }
}
