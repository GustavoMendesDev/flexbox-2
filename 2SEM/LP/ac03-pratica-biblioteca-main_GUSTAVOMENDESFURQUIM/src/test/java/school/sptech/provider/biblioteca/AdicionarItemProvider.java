package school.sptech.provider.biblioteca;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.BibliotecaFactory;
import school.sptech.factory.LivroFactory;
import school.sptech.factory.RevistaFactory;

public class AdicionarItemProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object l1 = LivroFactory.build("O Senhor dos Anéis", 2001, 59.90, "J.R.R. Tolkien", "FANTASIA");
        Object l2 = LivroFactory.build("Dom Casmurro", 2000, 39.90, "Machado de Assis", "ROMANCE");
        Object l3 = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR");
        Object r1 = RevistaFactory.build("National Geographic", 2020, 20.00, 150);
        Object r2 = RevistaFactory.build("Veja", 2021, 15.00, 2800);

        Object b1 = BibliotecaFactory.build("Biblioteca A", List.of());
        Object b2 = BibliotecaFactory.build("Biblioteca B", List.of(l1));
        Object b3 = BibliotecaFactory.build("Biblioteca C", List.of(l1, l2));
        Object b4 = BibliotecaFactory.build("Biblioteca D", List.of(r1));
        Object b5 = BibliotecaFactory.build("Biblioteca E", List.of(l1, r1));

        return Stream.of(
            Arguments.of("Biblioteca vazia adiciona livro", b1, l1, List.of(l1)),
            Arguments.of("Biblioteca com 1 livro adiciona outro", b2, l2, List.of(l1, l2)),
            Arguments.of("Biblioteca com 2 livros adiciona revista", b3, r1, List.of(l1, l2, r1)),
            Arguments.of("Biblioteca com revista adiciona livro", b4, l3, List.of(r1, l3)),
            Arguments.of("Biblioteca com livro e revista adiciona revista", b5, r2, List.of(l1, r1, r2))
        );
    }
}
