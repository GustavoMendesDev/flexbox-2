package school.sptech.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.Emprestavel;

@DisplayName("Emprestavel")
public class EmprestavelTest {

    @Nested
    @DisplayName("1. Interface")
    class InterfaceTest {

        @Test
        @DisplayName("1. Deve ser uma interface")
        void test1() {
            Class<Emprestavel> clazz = Emprestavel.class;
            assertAll(
                    () -> assertDoesNotThrow(() -> {
                        if (!clazz.isInterface()) {
                            throw new NoSuchMethodException("Emprestavel deve ser uma interface");
                        }
                    }, "Emprestavel deve ser uma interface")
            );
        }
    }

    @Nested
    @DisplayName("2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Emprestavel> clazz = Emprestavel.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getTitulo"), "Deve possuir o método getTitulo"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("emprestar"), "Deve possuir o método emprestar"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("devolver"), "Deve possuir o método devolver"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("estaEmprestado"), "Deve possuir o método estaEmprestado")
            );
        }
    }
}
