package school.sptech.provider.biblioteca;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.BibliotecaFactory;
import school.sptech.factory.UsuarioFactory;

public class CadastrarUsuarioProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object u1 = UsuarioFactory.build("Alice");
        Object u2 = UsuarioFactory.build("Bruno");
        Object u3 = UsuarioFactory.build("Carla");

        Object biblioteca = BibliotecaFactory.build("Biblioteca A", List.of(), List.of(u1, u2));

        return Stream.of(
              Arguments.of("Cadastra novo usuario", biblioteca, u3, List.of(u1, u2, u3))
        );
    }
}
