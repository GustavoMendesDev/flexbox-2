package school.sptech.provider.livro;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.LivroFactory;

public class BuscarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object l1 = LivroFactory.build("O Senhor dos Anéis", 2001, 59.90, "J.R.R. Tolkien", "FANTASIA");
        Object l2 = LivroFactory.build("Dom Casmurro", 2000, 39.90, "Machado de Assis", "ROMANCE");
        Object l3 = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR");
        Object l4 = LivroFactory.build("Duna", 2005, 54.90, "Frank Herbert", "FICCAO");
        Object l5 = LivroFactory.build("Steve Jobs", 2011, 44.90, "Walter Isaacson", "BIOGRAFIA");

        return Stream.of(
              Arguments.of(
                    "Busca por 'Tolkien' no livro \"O Senhor dos Anéis\" de \"J.R.R. Tolkien\" deve retornar true",
                    l1,
                    "Tolkien",
                    true
              ),

              Arguments.of(
                    "Busca por 'dom casmurro' no livro \"Dom Casmurro\" deve ignorar maiúsculas e minúsculas e retornar true",
                    l2,
                    "dom casmurro",
                    true
              ),

              Arguments.of(
                    "Busca por 'King' no livro \"O Iluminado\" de \"Stephen King\" deve retornar true",
                    l3,
                    "King",
                    true
              ),

              Arguments.of(
                    "Busca por 'Duna' no livro \"Duna\" deve retornar true",
                    l4,
                    "Duna",
                    true
              ),

              Arguments.of(
                    "Busca por 'Walter' no livro \"Steve Jobs\" de \"Walter Isaacson\" deve retornar true",
                    l5,
                    "Walter",
                    true
              ),

              Arguments.of(
                    "Busca por gênero 'terror' no livro \"O Senhor dos Anéis\" não deve considerar gênero e deve retornar false",
                    l1,
                    "terror",
                    false
              ),

              Arguments.of(
                    "Busca por 'Tolkien' no livro \"Dom Casmurro\" deve retornar false",
                    l2,
                    "Tolkien",
                    false
              ),

              Arguments.of(
                    "Busca por gênero 'romance' no livro \"O Iluminado\" não deve considerar gênero e deve retornar false",
                    l3,
                    "romance",
                    false
              ),

              Arguments.of(
                    "Busca por 'herbert' no livro \"Duna\" deve ignorar maiúsculas e minúsculas e retornar true",
                    l4,
                    "herbert",
                    true
              ),

              Arguments.of(
                    "Busca por 'steve' no livro \"Steve Jobs\" deve ignorar maiúsculas e minúsculas e retornar true",
                    l5,
                    "steve",
                    true
              ),

              Arguments.of(
                    "Busca por 'Anéis' no livro \"O Senhor dos Anéis\" deve encontrar parte do título e retornar true",
                    l1,
                    "Anéis",
                    true
              ),

              Arguments.of(
                    "Busca por 'Assis' no livro \"Dom Casmurro\" deve encontrar parte do autor e retornar true",
                    l2,
                    "Assis",
                    true
              ),

              Arguments.of(
                    "Busca por 'Iluminado' no livro \"O Iluminado\" deve encontrar parte do título e retornar true",
                    l3,
                    "Iluminado",
                    true
              ),

              Arguments.of(
                    "Busca por 'Tolkien' no livro \"Duna\" deve retornar false",
                    l4,
                    "Tolkien",
                    false
              ),

              Arguments.of(
                    "Busca por 'Jobs' no livro \"Steve Jobs\" deve encontrar parte do título e retornar true",
                    l5,
                    "Jobs",
                    true
              )
        );
    }
}
