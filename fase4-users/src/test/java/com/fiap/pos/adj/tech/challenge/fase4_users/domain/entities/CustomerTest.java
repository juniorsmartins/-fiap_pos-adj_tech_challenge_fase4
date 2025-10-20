package com.fiap.pos.adj.tech.challenge.fase4_users.domain.entities;

import com.fiap.pos.adj.tech.challenge.fase4_users.application.configs.exceptions.http400.FieldWithNullEmptyOrBlankValueIsProhibitedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {

    @Test
    void dadoNomeNull_quandoCriarCustomer_entaoLancarExcecao() {
        assertThrows(FieldWithNullEmptyOrBlankValueIsProhibitedException.class,
                () -> new Customer(UUID.randomUUID(), null, true, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void dadoNomeVazioOuEmBranco_quandoCriarCustomer_entaoLancarExcecao(String valor) {
        assertThrows(FieldWithNullEmptyOrBlankValueIsProhibitedException.class,
                () -> new Customer(null, valor, true, null));
    }
}