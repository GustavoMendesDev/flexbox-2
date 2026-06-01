package school.sptech.provider.biblioteca;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.BibliotecaFactory;
import school.sptech.factory.LivroFactory;
import school.sptech.factory.RevistaFactory;

public class BuscarItensDisponiveisProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        // Revistas não são Emprestavel, portanto nunca aparecem nos disponíveis
        Object l1 = LivroFactory.build("O Senhor dos Anéis", 2001, 59.90, "J.R.R. Tolkien", "FANTASIA", false);
        Object l2 = LivroFactory.build("Dom Casmurro", 2000, 39.90, "Machado de Assis", "ROMANCE", true);
        Object l3 = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR", false);
        Object l4 = LivroFactory.build("Duna", 2005, 54.90, "Frank Herbert", "FICCAO", true);
        Object l5 = LivroFactory.build("Steve Jobs", 2011, 44.90, "Walter Isaacson", "BIOGRAFIA", false);
        Object r1 = RevistaFactory.build("National Geographic", 2020, 20.00, 150);
        Object r2 = RevistaFactory.build("Veja", 2021, 15.00, 2800);

        Object b1 = BibliotecaFactory.build("Biblioteca A", List.of(l1, l2, r1));
        Object b2 = BibliotecaFactory.build("Biblioteca B", List.of(l2, l4));
        Object b3 = BibliotecaFactory.build("Biblioteca C", List.of());
        Object b4 = BibliotecaFactory.build("Biblioteca D", List.of(l1, l3, l5, r1, r2));
        Object b5 = BibliotecaFactory.build("Biblioteca E", List.of(l1, l2, l3, l4, l5));

        return Stream.of(
            Arguments.of("Alguns livros disponiveis", b1, List.of(l1)),
            Arguments.of("Todos emprestados", b2, List.of()),
            Arguments.of("Biblioteca vazia", b3, List.of()),
            Arguments.of("Livros e revistas, alguns disponiveis", b4, List.of(l1, l3, l5)),
            Arguments.of("Livros mistos com emprestados", b5, List.of(l1, l3, l5))
        );
    }
}
