package school.sptech.provider.revista;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.RevistaFactory;

public class CalcularPrecoFinalProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object r1 = RevistaFactory.build("National Geographic", 2020, 100.0, 150);  // 80.0
        Object r2 = RevistaFactory.build("Veja", 2021, 50.0, 2800);                 // 40.0
        Object r3 = RevistaFactory.build("Superinteressante", 2019, 25.0, 400);     // 20.0
        Object r4 = RevistaFactory.build("Scientific American", 2022, 40.0, 200);   // 32.0
        Object r5 = RevistaFactory.build("Piauí", 2018, 10.0, 185);                 // 8.0

        return Stream.of(
            Arguments.of(r1,100.0, 80.0),
            Arguments.of(r2, 50.0, 40.0),
            Arguments.of(r3, 25.0, 20.0),
            Arguments.of(r4, 40.0, 32.0),
            Arguments.of(r5, 10.0, 8.0)
        );
    }
}
