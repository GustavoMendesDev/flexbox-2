package school.sptech.provider.livro;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConstructorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
            Arguments.of("O Senhor dos Anéis", 2001, 59.90, "J.R.R. Tolkien", "FANTASIA"),
            Arguments.of("Steve Jobs", 2011, 44.90, "Walter Isaacson", "BIOGRAFIA")
        );
    }
}
