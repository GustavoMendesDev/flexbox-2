package school.sptech.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import school.sptech.Emprestavel;
import school.sptech.Genero;
import school.sptech.Item;
import school.sptech.Livro;
import school.sptech.Usuario;
import school.sptech.exception.EmprestavelIndisponivelException;
import school.sptech.factory.LivroFactory;
import school.sptech.provider.livro.BuscarProvider;
import school.sptech.provider.livro.CalcularPrecoFinalProvider;
import school.sptech.provider.livro.ConstructorProvider;
import school.sptech.util.EnumConstantMapping;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("Livro")
public class LivroTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Livro> clazz = Livro.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("autor"),
                        "Deve possuir o atributo autor"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("genero"),
                        "Deve possuir o atributo genero"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("emprestado"),
                        "Deve possuir o atributo emprestado")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() {
            Class<Livro> clazz = Livro.class;

            Stream<Executable> validacoes = Stream.of("autor", "genero")
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
            Class<Livro> clazz = Livro.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscar", String.class),
                        "Deve possuir o método buscar"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularPrecoFinal"),
                        "Deve possuir o método calcularPrecoFinal"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("emprestar"),
                        "Deve possuir o método emprestar"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("devolver"),
                        "Deve possuir o método devolver"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("estaEmprestado"),
                        "Deve possuir o método estaEmprestado")
            );
        }
    }

    @Nested
    @DisplayName("3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Livro> clazz = Livro.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Livro> clazz = Livro.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("buscar", String.class));
                metodos.add(clazz.getDeclaredMethod("calcularPrecoFinal"));
                metodos.add(clazz.getDeclaredMethod("emprestar"));
                metodos.add(clazz.getDeclaredMethod("devolver"));
                metodos.add(clazz.getDeclaredMethod("estaEmprestado"));
            } catch (ReflectiveOperationException ignored) {
            }

            Stream<Executable> validacoes = metodos.stream()
                  .map(metodo -> () -> {
                      assertTrue(Modifier.isPublic(metodo.getModifiers()));
                  });

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Livro> clazz = Livro.class;
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

        @ParameterizedTest(name = "Cenário {index}: titulo=\"{0}\", ano={1}, precoBase={2}, autor=\"{3}\", genero=\"{4}\"")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4(String titulo, Integer ano, Double precoBase, String autor, String genero)
              throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;

            assertDoesNotThrow(() -> {
                clazz.getDeclaredConstructor(String.class, Integer.class, Double.class,
                      String.class, Genero.class);
            }, "Construtor com parâmetros deve existir");

            Genero generoEnum = EnumConstantMapping.getEnumConstant(Genero.class, genero);
            Object obj = clazz.getDeclaredConstructor(String.class, Integer.class, Double.class,
                        String.class, Genero.class)
                  .newInstance(titulo, ano, precoBase, autor, generoEnum);

            ObjectFieldMapping<Livro> mapping = new ObjectFieldMapping<>(Livro.class);

            assertAll(
                  () -> assertEquals(titulo, mapping.get("titulo").get(obj),
                        "Titulo deve ser inicializado corretamente"),
                  () -> assertEquals(ano, mapping.get("ano").get(obj),
                        "Ano deve ser inicializado corretamente"),
                  () -> assertEquals(precoBase, mapping.get("precoBase").get(obj),
                        "PrecoBase deve ser inicializado corretamente"),
                  () -> assertEquals(autor, mapping.get("autor").get(obj),
                        "Autor deve ser inicializado corretamente"),
                  () -> assertEquals(generoEnum, mapping.get("genero").get(obj),
                        "Genero deve ser inicializado corretamente"),
                  () -> assertEquals(false, mapping.get("emprestado").get(obj),
                        "Emprestado deve ser false por padrão")
            );
        }
    }

    @Nested
    @DisplayName("4. Herança")
    class HerancaTest {

        @Test
        @DisplayName("1. Livro deve herdar de Item")
        void test1() {
            assertTrue(Item.class.isAssignableFrom(Livro.class),
                  "Livro deve herdar da classe Item");
        }
    }

    @Nested
    @DisplayName("5. Implementa Emprestavel")
    class ImplementaEmprestavelTest {

        @Test
        @DisplayName("1. Livro deve implementar Emprestavel")
        void test1() {
            assertTrue(Emprestavel.class.isAssignableFrom(Livro.class),
                  "Livro deve implementar a interface Emprestavel");
        }
    }

    @Nested
    @DisplayName("6. Método - buscar")
    class MetodoBuscarTest {

        @DisplayName("Teste 1")
        @ParameterizedTest(name = "Cenário {index}: {0}")
        @ArgumentsSource(BuscarProvider.class)
        void test1(String cenario, Object obj, Object query, Boolean expected)
              throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;
            Method method = clazz.getDeclaredMethod("buscar", String.class);

            Object response = method.invoke(obj, (String) query);

            assertEquals(expected, response);
        }
    }

    @Nested
    @DisplayName("7. Método - calcularPrecoFinal")
    class MetodoCalcularPrecoFinalTest {

        @ParameterizedTest(name = "Cenário {index}: genero={4}, precoBase={2} => precoFinal={5}")
        @DisplayName("Deve calcular o preço final corretamente")
        @ArgumentsSource(CalcularPrecoFinalProvider.class)
        void test1(String titulo, int ano, double precoBase, String autor, String genero,
              Double expected) throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;
            Object obj = LivroFactory.build(titulo, ano, precoBase, autor, genero);
            Method method = clazz.getDeclaredMethod("calcularPrecoFinal");
            Object actual = method.invoke(obj);
            assertEquals(expected, (Double) actual, 0.01);
        }
    }

    @Nested
    @DisplayName("8. Método - getTitulo")
    class MetodoGetTituloTest {

        @Test
        @DisplayName("Deve retornar o título corretamente")
        void test1() throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;
            Object obj = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR",
                  false);
            Method getTitulo = clazz.getMethod("getTitulo");
            Object response = getTitulo.invoke(obj);
            assertEquals("O Iluminado", response);
        }
    }

    @Nested
    @DisplayName("9. Método - emprestar")
    class MetodoEmprestarTest {

        @Test
        @DisplayName("Deve emprestar corretamente")
        void test1() throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;
            Object obj = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR",
                  false);
            Method emprestar = clazz.getDeclaredMethod("emprestar");
            Field emprestadoField = clazz.getDeclaredField("emprestado");
            emprestadoField.trySetAccessible();
            emprestar.invoke(obj);
            assertTrue((Boolean) emprestadoField.get(obj),
                  "Livro deve estar emprestado após emprestar");
        }

        @Test
        @DisplayName("Deve lançar exceção ao emprestar livro já emprestado")
        void test2() throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;
            Method emprestar = clazz.getDeclaredMethod("emprestar");
            Object obj = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR",
                  true);
            Exception exception = assertThrows(Exception.class, () -> emprestar.invoke(obj),
                  "Deve lançar exceção ao tentar emprestar livro já emprestado");
            assertTrue(EmprestavelIndisponivelException.class.isAssignableFrom(
                        exception.getCause().getClass()),
                  "Deve lançar EmprestavelIndisponivelException ao tentar emprestar livro já emprestado");
        }
    }

    @Nested
    @DisplayName("10. Método - devolver")
    class MetodoDevolverTest {

        @Test
        @DisplayName("Deve devolver corretamente")
        void test1() throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;
            Method devolver = clazz.getDeclaredMethod("devolver");
            Object obj = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR",
                  true);
            devolver.invoke(obj);
            Field emprestadoField = clazz.getDeclaredField("emprestado");
            emprestadoField.trySetAccessible();
            assertFalse((Boolean) emprestadoField.get(obj),
                  "Livro deve estar disponível após devolver");
        }
    }

    @Nested
    @DisplayName("11. Método - estaEmprestado")
    class MetodoEstaEmprestadoTest {

        @Test
        @DisplayName("Deve retornar true quando o livro estiver emprestado")
        void test1() throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;
            Method estaEmprestado = clazz.getDeclaredMethod("estaEmprestado");
            Object obj = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR",
                  true);
            Object response = estaEmprestado.invoke(obj);
            assertTrue((Boolean) response, "Deve retornar true quando o livro estiver emprestado");
        }

        @Test
        @DisplayName("Deve retornar false quando o livro não estiver emprestado")
        void test2() throws ReflectiveOperationException {
            Class<Livro> clazz = Livro.class;
            Method estaEmprestado = clazz.getDeclaredMethod("estaEmprestado");
            Object obj = LivroFactory.build("O Iluminado", 1999, 49.90, "Stephen King", "TERROR",
                  false);
            Object response = estaEmprestado.invoke(obj);
            assertFalse((Boolean) response,
                  "Deve retornar false quando o livro não estiver emprestado");
        }
    }
}
