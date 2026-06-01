package school.sptech.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Biblioteca;
import school.sptech.Emprestavel;
import school.sptech.Usuario;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.Item;
import school.sptech.factory.BibliotecaFactory;
import school.sptech.provider.biblioteca.AdicionarItemProvider;
import school.sptech.provider.biblioteca.BuscarItensDisponiveisProvider;
import school.sptech.provider.biblioteca.BuscarItensProvider;
import school.sptech.provider.biblioteca.CadastrarUsuarioProvider;
import school.sptech.provider.biblioteca.ConstructorProvider;
import school.sptech.provider.biblioteca.EmprestarProvider;
import school.sptech.provider.biblioteca.RemoverItemProvider;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("Biblioteca")
public class BibliotecaTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Biblioteca> clazz = Biblioteca.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("itens"),
                        "Deve possuir o atributo itens"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("usuarios"),
                        "Deve possuir o atributo usuarios")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() {
            Class<Biblioteca> clazz = Biblioteca.class;

            Stream<Executable> validacoes = Stream.of("nome", "itens", "usuarios")
                  .map(nomeCampo -> () -> {
                      Field campo = clazz.getDeclaredField(nomeCampo);
                      assertTrue(Modifier.isFinal(campo.getModifiers()),
                            String.format("%s deve ser final", nomeCampo));
                  });

            assertAll(validacoes);
        }
    }

    @Nested
    @DisplayName("2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Biblioteca> clazz = Biblioteca.class;

            assertAll(
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("adicionarItem", Item.class),
                        "Deve possuir o método adicionarItem"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("cadastrarUsuario", Usuario.class),
                        "Deve possuir o método cadastrarUsuario"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("removerItem", String.class),
                        "Deve possuir o método removerItem"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("buscarItens", String.class),
                        "Deve possuir o método buscarItens"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarItensDisponiveis"),
                        "Deve possuir o método buscarItensDisponiveis"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("emprestar", String.class, String.class),
                        "Deve possuir o método emprestar")
            );
        }
    }

    @Nested
    @DisplayName("3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Biblioteca> clazz = Biblioteca.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Biblioteca> clazz = Biblioteca.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("adicionarItem", Item.class));
                metodos.add(clazz.getDeclaredMethod("cadastrarUsuario", Usuario.class));
                metodos.add(clazz.getDeclaredMethod("removerItem", String.class));
                metodos.add(clazz.getDeclaredMethod("buscarItens", String.class));
                metodos.add(clazz.getDeclaredMethod("buscarItensDisponiveis"));
                metodos.add(clazz.getDeclaredMethod("emprestar", String.class, String.class));
            } catch (ReflectiveOperationException ignored) {
            }

            Stream<Executable> validacoes = metodos.stream()
                  .map((metodo) -> () -> {
                      assertTrue(Modifier.isPublic(metodo.getModifiers()),
                            String.format("%s deve ser público", metodo.getName()));
                  });

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Biblioteca> clazz = Biblioteca.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map(campo -> () -> {
                      String getName = String.format("get%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          assertTrue(Modifier.isPublic(getter.getModifiers()),
                                String.format("Getter %s deve ser público", getName));
                      }, String.format("Deve possuir o getter %s", getName));
                  });

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                  .map(campo -> () -> {
                      String setName = String.format("set%s",
                            StringUtils.capitalize(campo.getName()));
                      Assertions.assertThrows(NoSuchMethodException.class, () -> {
                          clazz.getDeclaredMethod(setName, campo.getType());
                      }, String.format("%s não deve possuir setter", campo.getName()));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }

        @ParameterizedTest(name = "Cenário {index}: nome=\"{0}\"")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4(String nome) throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;

            assertDoesNotThrow(() -> {
                clazz.getDeclaredConstructor(String.class);
            }, "Construtor com parâmetro (String nome) deve existir");

            Object obj = clazz.getDeclaredConstructor(String.class).newInstance(nome);

            ObjectFieldMapping<Biblioteca> mapping = new ObjectFieldMapping<>(Biblioteca.class);

            assertAll(
                  () -> assertEquals(nome, mapping.get("nome").get(obj),
                        "Nome deve ser inicializado corretamente"),
                  () -> assertIterableEquals(List.of(), (List<?>) mapping.get("itens").get(obj),
                        "Itens deve ser inicializado como lista vazia"),
                  () -> assertIterableEquals(List.of(), (List<?>) mapping.get("usuarios").get(obj),
                        "Usuarios deve ser inicializado como lista vazia")
            );
        }
    }

    @Nested
    @DisplayName("4. Método - adicionarItem")
    class AdicionarItemTest {

        @ParameterizedTest(name = "Cenário {index}: {0}")
        @ArgumentsSource(AdicionarItemProvider.class)
        @DisplayName("1. Deve adicionar itens corretamente")
        void test1(String cenario, Object biblioteca, Object item, List<?> expected)
              throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;
            Method adicionarItemMethod = clazz.getDeclaredMethod("adicionarItem", Item.class);

            adicionarItemMethod.invoke(biblioteca, (Item) item);

            ObjectFieldMapping<Biblioteca> mapping = new ObjectFieldMapping<>(Biblioteca.class);
            List<?> atualItens = (List<?>) mapping.get("itens").get(biblioteca);

            assertIterableEquals(expected, atualItens,
                  "Lista de itens deve ser atualizada corretamente após adicionar item");
        }

        @Test
        @DisplayName("2. Deve lançar DadoInvalidoException ao adicionar item nulo")
        void test2() throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;
            Method adicionarItemMethod = clazz.getDeclaredMethod("adicionarItem", Item.class);

            Biblioteca biblioteca = (Biblioteca) BibliotecaFactory.build("Biblioteca Teste",
                  List.of());
            var exception = assertThrows(Exception.class,
                  () -> adicionarItemMethod.invoke(biblioteca, (Item) null),
                  "Deve lançar DadoInvalidoException ao adicionar item nulo");
            assertTrue(
                  DadoInvalidoException.class.isAssignableFrom(exception.getCause().getClass()),
                  "Exceção lançada deve ser do tipo DadoInvalidoException");
        }
    }

    @Nested
    @DisplayName("5. Método - cadastrarUsuario")
    class CadastrarUsuarioTest {

        @ParameterizedTest(name = "Cenário {index}: {0}")
        @ArgumentsSource(CadastrarUsuarioProvider.class)
        @DisplayName("1. Deve cadastrar usuários corretamente")
        void test1(String cenario, Object biblioteca, Object usuario, List<?> expected)
              throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;
            Method cadastrarUsuarioMethod = clazz.getDeclaredMethod("cadastrarUsuario",
                  Usuario.class);

            cadastrarUsuarioMethod.invoke(biblioteca, (Usuario) usuario);

            ObjectFieldMapping<Biblioteca> mapping = new ObjectFieldMapping<>(Biblioteca.class);
            List<?> atualUsuarios = (List<?>) mapping.get("usuarios").get(biblioteca);

            assertIterableEquals(expected, atualUsuarios,
                  "Lista de usuários deve ser atualizada corretamente após cadastrar usuário");
        }

        @Test
        @DisplayName("2. Deve lançar DadoInvalidoException ao cadastrar usuário nulo")
        void test2() throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;
            Method cadastrarUsuarioMethod = clazz.getDeclaredMethod("cadastrarUsuario",
                  Usuario.class);

            Biblioteca biblioteca = (Biblioteca) BibliotecaFactory.build("Biblioteca Teste",
                  List.of());
            var exception = assertThrows(Exception.class,
                  () -> cadastrarUsuarioMethod.invoke(biblioteca, (Usuario) null),
                  "Deve lançar DadoInvalidoException ao cadastrar usuário nulo");
            assertTrue(
                  DadoInvalidoException.class.isAssignableFrom(exception.getCause().getClass()),
                  "Exceção lançada deve ser do tipo DadoInvalidoException");
        }
    }

    @Nested
    @DisplayName("6. Método - removerItem")
    class RemoverItemTest {

        @ParameterizedTest(name = "Cenário {index}: {0}")
        @ArgumentsSource(RemoverItemProvider.class)
        @DisplayName("1. Deve remover itens corretamente")
        void test1(String cenario, Object biblioteca, String titulo, List<?> expected)
              throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;
            Method removerItemMethod = clazz.getDeclaredMethod("removerItem", String.class);

            removerItemMethod.invoke(biblioteca, titulo);

            ObjectFieldMapping<Biblioteca> mapping = new ObjectFieldMapping<>(Biblioteca.class);
            List<?> atualItens = (List<?>) mapping.get("itens").get(biblioteca);

            assertIterableEquals(expected, atualItens,
                  "Lista de itens deve ser atualizada corretamente após remover item");
        }
    }

    @Nested
    @DisplayName("7. Método - buscarItens")
    class BuscarItensTest {

        @ParameterizedTest(name = "Cenário {index}: {0}")
        @ArgumentsSource(BuscarItensProvider.class)
        @DisplayName("1. Deve buscar itens corretamente")
        void test1(String cenario, Object biblioteca, String texto, List<?> expected)
              throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;
            Method buscarItensMethod = clazz.getDeclaredMethod("buscarItens", String.class);

            List<?> resultados = (List<?>) buscarItensMethod.invoke(biblioteca, texto);

            assertIterableEquals(expected, resultados,
                  "Resultados da busca devem corresponder ao esperado");
        }
    }

    @Nested
    @DisplayName("8. Método - buscarItensDisponiveis")
    class BuscarItensDisponiveisTest {

        @ParameterizedTest(name = "Cenário {index}: {0}")
        @ArgumentsSource(BuscarItensDisponiveisProvider.class)
        @DisplayName("1. Deve retornar apenas itens disponíveis (Emprestavel e não emprestado)")
        void test1(String cenario, Object biblioteca, List<?> expected)
              throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;
            Method buscarDisponiveisMethod = clazz.getDeclaredMethod("buscarItensDisponiveis");

            List<?> resultados = (List<?>) buscarDisponiveisMethod.invoke(biblioteca);

            assertIterableEquals(expected, resultados, "Deve retornar somente itens disponíveis");
        }
    }

    @Nested
    @DisplayName("9. Método - emprestar")
    class EmprestarTest {

        @ParameterizedTest(name = "Cenário {index}: {0}")
        @ArgumentsSource(EmprestarProvider.class)
        @DisplayName("1. Deve emprestar item ao usuário corretamente")
        void test1(String cenario, Object biblioteca, String tituloLivro, String nomeUsuario,
              Object itemEsperado, Object usuarioEsperado, Boolean esperadoEmprestado,
              boolean esperadoUsuarioContem) throws ReflectiveOperationException {
            Class<Biblioteca> clazz = Biblioteca.class;
            Method emprestar = clazz.getDeclaredMethod("emprestar", String.class, String.class);

            emprestar.invoke(biblioteca, tituloLivro, nomeUsuario);

            if (esperadoEmprestado != null) {
                // Verify item is marked as emprestado
                Method estaEmprestado = itemEsperado.getClass().getDeclaredMethod("estaEmprestado");
                assertEquals(esperadoEmprestado, (Boolean) estaEmprestado.invoke(itemEsperado),
                      "Emprestavel deve estar com status de empréstimo esperado");
            } else {
                assertFalse(itemEsperado instanceof Emprestavel,
                      "Item não emprestável não deve possuir status de empréstimo");
            }

            // Verify usuario has the item in itensEmprestados
            ObjectFieldMapping<?> usuarioMapping = new ObjectFieldMapping<>(
                  usuarioEsperado.getClass());
            List<?> itensEmprestados = (List<?>) usuarioMapping.get("itensEmprestados")
                  .get(usuarioEsperado);
            assertEquals(esperadoUsuarioContem, itensEmprestados.contains(itemEsperado),
                  "Usuário deve conter o item conforme esperado");
        }
    }
}
