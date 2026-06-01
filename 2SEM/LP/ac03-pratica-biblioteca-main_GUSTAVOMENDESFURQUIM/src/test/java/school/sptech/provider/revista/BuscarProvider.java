package school.sptech.provider.revista;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.RevistaFactory;

public class BuscarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object r1 = RevistaFactory.build("National Geographic", 2020, 20.00, 150);
        Object r2 = RevistaFactory.build("Veja", 2021, 15.00, 2800);
        Object r3 = RevistaFactory.build("Superinteressante", 2019, 18.00, 400);
        Object r4 = RevistaFactory.build("Scientific American", 2022, 25.00, 200);

        return Stream.of(
              Arguments.of(
                    "Busca por 'National' na revista \"National Geographic\" deve retornar true",
                    r1,
                    "National",
                    true
              ),

              Arguments.of(
                    "Busca por 'veja' na revista \"Veja\" deve ignorar maiúsculas e minúsculas e retornar true",
                    r2,
                    "veja",
                    true
              ),

              Arguments.of(
                    "Busca por 'Interessante' na revista \"Superinteressante\" deve retornar true",
                    r3,
                    "Interessante",
                    true
              ),

              Arguments.of(
                    "Busca por 'scientific' na revista \"Scientific American\" deve ignorar maiúsculas e minúsculas e retornar true",
                    r4,
                    "scientific",
                    true
              ),

              Arguments.of(
                    "Busca por 'geographic' na revista \"National Geographic\" deve retornar true",
                    r1,
                    "geographic",
                    true
              ),

              Arguments.of(
                    "Busca por 'National' na revista \"Veja\" deve retornar false",
                    r2,
                    "National",
                    false
              ),

              Arguments.of(
                    "Busca por 'Veja' na revista \"Superinteressante\" deve retornar false",
                    r3,
                    "Veja",
                    false
              ),

              Arguments.of(
                    "Busca por 'Geographic' na revista \"Scientific American\" deve retornar false",
                    r4,
                    "Geographic",
                    false
              ),

              Arguments.of(
                    "Busca por ano '2020' na revista \"National Geographic\" não deve considerar ano e deve retornar false",
                    r1,
                    "2020",
                    false
              ),

              Arguments.of(
                    "Busca por 'super' na revista \"Superinteressante\" deve ignorar maiúsculas e minúsculas e retornar true",
                    r3,
                    "super",
                    true
              )
        );
    }
}
