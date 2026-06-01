package school.sptech.provider.livro;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularPrecoFinalProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {

        return Stream.of(
              Arguments.of("O Senhor dos Anéis", 2001, 100.0, "J.R.R. Tolkien", "FANTASIA",
                    100.0),
              Arguments.of("Dom Casmurro", 2000, 50.0, "Machado de Assis", "ROMANCE",
                    50.0),
              Arguments.of("O Iluminado", 1999, 80.0, "Stephen King", "TERROR",
                    80.0),
              Arguments.of("Duna", 2005, 60.0, "Frank Herbert", "FICCAO",
                    60.0),
              Arguments.of("Steve Jobs", 2011, 40.0, "Walter Isaacson", "BIOGRAFIA",
                    32.0)
        );
    }
}
