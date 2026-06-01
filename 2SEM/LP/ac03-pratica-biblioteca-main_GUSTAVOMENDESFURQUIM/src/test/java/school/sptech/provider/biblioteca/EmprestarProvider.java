package school.sptech.provider.biblioteca;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.BibliotecaFactory;
import school.sptech.factory.LivroFactory;
import school.sptech.factory.RevistaFactory;
import school.sptech.factory.UsuarioFactory;

public class EmprestarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object l1 = LivroFactory.build("O Senhor dos Anéis", 2001, 59.90, "J.R.R. Tolkien",
              "FANTASIA", false);
        Object u1 = UsuarioFactory.build("Alice");
        Object b1 = BibliotecaFactory.build("Biblioteca A", List.of(l1), List.of(u1));

        Object l2 = LivroFactory.build("Dom Casmurro", 2000, 39.90, "Machado de Assis", "ROMANCE",
              false);
        Object u2 = UsuarioFactory.build("Bob");
        Object b2 = BibliotecaFactory.build("Biblioteca B", List.of(l2), List.of(u2));

        Object l3 = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR",
              false);
        Object u3 = UsuarioFactory.build("Diana");
        Object b3 = BibliotecaFactory.build("Biblioteca C", List.of(l3), List.of(u3));

        Object r1 = RevistaFactory.build("Veja", 2021, 15.00, 2800);
        Object u4 = UsuarioFactory.build("Eva");
        Object b4 = BibliotecaFactory.build("Biblioteca D", List.of(r1), List.of(u4));

        Object l4 = LivroFactory.build("Duna", 2005, 54.90, "Frank Herbert", "FICCAO", true);
        Object u5 = UsuarioFactory.build("Felipe");
        Object b5 = BibliotecaFactory.build("Biblioteca E", List.of(l4), List.of(u5));

        return Stream.of(
              Arguments.of(
                    "Usuario encontrado e livro disponivel deve emprestar",
                    b1,
                    "O Senhor dos Anéis",
                    "Alice",
                    l1,
                    u1,
                    true,
                    true
              ),
              Arguments.of(
                    "Usuario nao encontrado deve fazer nada",
                    b2,
                    "Dom Casmurro",
                    "Carlos",
                    l2,
                    u2,
                    false,
                    false
              ),
              Arguments.of(
                    "Item nao encontrado deve fazer nada",
                    b3,
                    "Inexistente",
                    "Diana",
                    l3,
                    u3,
                    false,
                    false
              ),
              Arguments.of(
                    "Item nao emprestavel deve fazer nada",
                    b4,
                    "Veja",
                    "Eva",
                    r1,
                    u4,
                    null,
                    false
              ),
              Arguments.of(
                    "Item ja emprestado deve fazer nada",
                    b5,
                    "Duna",
                    "Felipe",
                    l4,
                    u5,
                    true,
                    false
              )
        );
    }
}
