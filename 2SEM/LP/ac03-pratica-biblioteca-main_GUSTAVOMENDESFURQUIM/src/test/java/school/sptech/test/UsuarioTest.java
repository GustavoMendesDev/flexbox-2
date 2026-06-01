package school.sptech.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
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
import school.sptech.Emprestavel;
import school.sptech.Usuario;
import school.sptech.provider.usuario.AdicionarEmprestimoProvider;
import school.sptech.provider.usuario.ConstructorProvider;
import school.sptech.provider.usuario.DevolverEmprestimoProvider;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("Usuario")
public class UsuarioTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Usuario> clazz = Usuario.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("itensEmprestados"),
                        "Deve possuir o atributo itensEmprestados")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() {
            Class<Usuario> clazz = Usuario.class;

            Stream<Executable> validacoes = Stream.of("nome", "itensEmprestados")
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
            Class<Usuario> clazz = Usuario.class;

            assertAll(
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("adicionarEmprestimo", Emprestavel.class),
                        "Deve possuir o método adicionarEmprestimo"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("devolverEmprestimo", Emprestavel.class),
                        "Deve possuir o método devolverEmprestimo")
            );
        }
    }

    @Nested
    @DisplayName("3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Usuario> clazz = Usuario.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Usuario> clazz = Usuario.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("adicionarEmprestimo", Emprestavel.class));
                metodos.add(clazz.getDeclaredMethod("devolverEmprestimo", Emprestavel.class));
            } catch (ReflectiveOperationException ignored) {
            }

            Stream<Executable> validacoes = metodos.stream()
                  .map(metodo -> () -> assertTrue(Modifier.isPublic(metodo.getModifiers()),
                        String.format("%s deve ser público", metodo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Usuario> clazz = Usuario.class;
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
            Class<Usuario> clazz = Usuario.class;

            assertDoesNotThrow(() -> clazz.getDeclaredConstructor(String.class),
                  "Construtor com parâmetro (String nome) deve existir");

            Object obj = clazz.getDeclaredConstructor(String.class).newInstance(nome);

            ObjectFieldMapping<Usuario> mapping = new ObjectFieldMapping<>(Usuario.class);

            assertAll(
                  () -> assertEquals(nome, mapping.get("nome").get(obj),
                        "Nome deve ser inicializado corretamente"),
                  () -> assertIterableEquals(List.of(),
                        (List<?>) mapping.get("itensEmprestados").get(obj),
                        "itensEmprestados deve ser inicializado como lista vazia")
            );
        }
    }

    @Nested
    @DisplayName("4. Método - adicionarEmprestimo")
    class AdicionarEmprestimoTest {

        @ParameterizedTest(name = "Cenário {index}: {0}")
        @DisplayName("Deve adicionar empréstimo corretamente")
        @ArgumentsSource(AdicionarEmprestimoProvider.class)
        void test1(String cenario, Object usuario, Object item, List<?> expected)
              throws ReflectiveOperationException {
            Class<Usuario> clazz = Usuario.class;
            Method method = clazz.getDeclaredMethod("adicionarEmprestimo", Emprestavel.class);

            method.invoke(usuario, (Emprestavel) item);

            ObjectFieldMapping<Usuario> mapping = new ObjectFieldMapping<>(Usuario.class);
            List<?> itensEmprestados = (List<?>) mapping.get("itensEmprestados").get(usuario);

            assertIterableEquals(expected, itensEmprestados,
                  "Lista de itens emprestados deve ser atualizada corretamente");
        }
    }

    @Nested
    @DisplayName("5. Método - devolverEmprestimo")
    class DevolverEmprestimoTest {

        @ParameterizedTest(name = "Cenário {index}: {0}")
        @DisplayName("Deve remover empréstimo corretamente")
        @ArgumentsSource(DevolverEmprestimoProvider.class)
        void test1(String cenario, Object usuario, Object item, List<?> expected)
              throws ReflectiveOperationException {
            Class<Usuario> clazz = Usuario.class;
            Method method = clazz.getDeclaredMethod("devolverEmprestimo", Emprestavel.class);

            method.invoke(usuario, (Emprestavel) item);

            ObjectFieldMapping<Usuario> mapping = new ObjectFieldMapping<>(Usuario.class);
            List<?> itensEmprestados = (List<?>) mapping.get("itensEmprestados").get(usuario);

            assertIterableEquals(expected, itensEmprestados,
                  "Lista de itens emprestados deve ser atualizada após devolução");
        }
    }
}
