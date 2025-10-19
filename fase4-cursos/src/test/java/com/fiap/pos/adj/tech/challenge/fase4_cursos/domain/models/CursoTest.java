package com.fiap.pos.adj.tech.challenge.fase4_cursos.domain.models;

import com.fiap.pos.adj.tech.challenge.fase4_cursos.application.configs.exceptions.http400.FieldWithNullEmptyOrBlankValueIsProhibitedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CursoTest {

    @Test
    void dadoNomeNull_quandoCriarCurso_entaoLancarExcecao() {
        var idQualquer = UUID.randomUUID();
        assertThrows(FieldWithNullEmptyOrBlankValueIsProhibitedException.class, () -> new Curso(idQualquer, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void dadoNomeVazioOuEmBranco_quandoCriarCurso_entaoLancarExcecao(String valor) {
        assertThrows(FieldWithNullEmptyOrBlankValueIsProhibitedException.class, () -> new Curso(null, valor));
    }
}