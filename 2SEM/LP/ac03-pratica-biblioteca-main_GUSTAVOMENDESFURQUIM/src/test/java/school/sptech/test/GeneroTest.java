package school.sptech.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.Genero;

@DisplayName("Genero")
public class GeneroTest {

    @Nested
    @DisplayName("1. Constantes")
    class ConstantesTest {

        @Test
        @DisplayName("1. Validar Constantes")
        void test1() {
            Class<Genero> clazz = Genero.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("FANTASIA")),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("ROMANCE")),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("TERROR")),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("FICCAO")),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("BIOGRAFIA"))
            );
        }
    }
}
