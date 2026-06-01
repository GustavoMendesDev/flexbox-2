package school.sptech.provider.item;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConstrutorValidacoesProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        return Stream.of(
              Arguments.of(null, 2020, 10.0),
              Arguments.of(" ", 2020, 10.0),
              Arguments.of("Item", null, 10.0),
              Arguments.of("Item", 1899, 10.0),
              Arguments.of("Item", 2020, null),
              Arguments.of("Item", 2020, -1.0)
        );
    }
}
