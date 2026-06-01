package school.sptech.provider.biblioteca;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.BibliotecaFactory;
import school.sptech.factory.LivroFactory;
import school.sptech.factory.RevistaFactory;

public class BuscarItensProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object l1 = LivroFactory.build("O Senhor dos Anéis", 2001, 59.90, "J.R.R. Tolkien", "FANTASIA");
        Object l2 = LivroFactory.build("Dom Casmurro", 2000, 39.90, "Machado de Assis", "ROMANCE");
        Object l3 = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR");
        Object l4 = LivroFactory.build("Duna", 2005, 54.90, "Frank Herbert", "FICCAO");
        Object l5 = LivroFactory.build("Steve Jobs", 2011, 44.90, "Walter Isaacson", "BIOGRAFIA");
        Object r1 = RevistaFactory.build("National Geographic", 2020, 20.00, 150);
        Object r2 = RevistaFactory.build("Veja", 2021, 15.00, 2800);

        Object b1 = BibliotecaFactory.build("Biblioteca A", List.of(l1, l2, r1));
        Object b2 = BibliotecaFactory.build("Biblioteca B", List.of(l3, l4, r2));
        Object b3 = BibliotecaFactory.build("Biblioteca C", List.of());
        Object b4 = BibliotecaFactory.build("Biblioteca D", List.of(l1, l2, l3, l4, l5, r1, r2));

        return Stream.of(
              Arguments.of(
                    "Busca por 'Tolkien' em biblioteca com \"O Senhor dos Anéis\", \"Dom Casmurro\" e \"National Geographic\" deve retornar apenas \"O Senhor dos Anéis\"",
                    b1,
                    "Tolkien",
                    List.of(l1)
              ),

              Arguments.of(
                    "Busca por 'Casmurro' em biblioteca com \"O Senhor dos Anéis\", \"Dom Casmurro\" e \"National Geographic\" deve retornar apenas \"Dom Casmurro\"",
                    b1,
                    "Casmurro",
                    List.of(l2)
              ),

              Arguments.of(
                    "Busca por 'National' em biblioteca com \"O Senhor dos Anéis\", \"Dom Casmurro\" e \"National Geographic\" deve retornar apenas \"National Geographic\"",
                    b1,
                    "National",
                    List.of(r1)
              ),

              Arguments.of(
                    "Busca por 'Stephen' em biblioteca com \"O Iluminado\", \"Duna\" e \"Veja\" deve retornar apenas \"O Iluminado\"",
                    b2,
                    "Stephen",
                    List.of(l3)
              ),

              Arguments.of(
                    "Busca por 'Duna' em biblioteca com \"O Iluminado\", \"Duna\" e \"Veja\" deve retornar apenas \"Duna\"",
                    b2,
                    "Duna",
                    List.of(l4)
              ),

              Arguments.of(
                    "Busca em biblioteca vazia deve retornar lista vazia",
                    b3,
                    "qualquer",
                    List.of()
              ),

              Arguments.of(
                    "Busca por 'o' em biblioteca com \"O Senhor dos Anéis\", \"Dom Casmurro\", \"O Iluminado\", \"Duna\", \"Steve Jobs\", \"National Geographic\" e \"Veja\" deve retornar todos os itens que contém a letra",
                    b4,
                    "o",
                    List.of(l1, l2, l3, l5, r1)
              ),

              Arguments.of(
                    "Busca por 'eja' em biblioteca com livros e revistas deve retornar apenas a revista \"Veja\"",
                    b4,
                    "eja",
                    List.of(r2)
              ),

              Arguments.of(
                    "Busca por texto inexistente em biblioteca com itens cadastrados deve retornar lista vazia",
                    b1,
                    "xyz",
                    List.of()
              ),

              Arguments.of(
                    "Busca por 'Herbert' em biblioteca com \"O Iluminado\", \"Duna\" e \"Veja\" deve retornar apenas \"Duna\"",
                    b2,
                    "Herbert",
                    List.of(l4)
              )
        );
    }
}
