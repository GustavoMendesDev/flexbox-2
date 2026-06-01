package school.sptech.provider.biblioteca;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.BibliotecaFactory;
import school.sptech.factory.LivroFactory;
import school.sptech.factory.RevistaFactory;

public class RemoverItemProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object l1a = LivroFactory.build("Livro A", 2000, 40.0, "Autor A", "ROMANCE");
        Object r1a = RevistaFactory.build("Revista A", 2020, 15.0, 100);
        Object l2a = LivroFactory.build("Livro B", 2005, 45.0, "Autor B", "FANTASIA");

        Object biblioteca1 = BibliotecaFactory.build("Biblioteca A", List.of(l1a, r1a, l2a));

        Object l1b = LivroFactory.build("Livro A", 2000, 40.0, "Autor A", "ROMANCE");
        Object r1b = RevistaFactory.build("Revista A", 2020, 15.0, 100);
        Object l2b = LivroFactory.build("Livro B", 2005, 45.0, "Autor B", "FANTASIA");

        Object biblioteca2 = BibliotecaFactory.build("Biblioteca B", List.of(l1b, r1b, l2b));

        return Stream.of(
              Arguments.of("Remove item existente ignorando case", biblioteca1, "revista a", List.of(l1a, l2a)),
              Arguments.of("Item inexistente nao altera lista", biblioteca2, "Inexistente",
                    List.of(l1b, r1b, l2b))
        );
    }
}
