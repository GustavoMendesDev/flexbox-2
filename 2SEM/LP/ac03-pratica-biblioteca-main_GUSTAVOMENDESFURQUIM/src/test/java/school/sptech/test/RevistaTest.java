package school.sptech.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import school.sptech.Item;
import school.sptech.Revista;
import school.sptech.Usuario;
import school.sptech.provider.revista.BuscarProvider;
import school.sptech.provider.revista.CalcularPrecoFinalProvider;
import school.sptech.provider.revista.ConstructorProvider;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("Revista")
public class RevistaTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Revista> clazz = Revista.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("edicao"),
                        "Deve possuir o atributo edicao")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() {
            Class<Revista> clazz = Revista.class;

            Stream<Executable> validacoes = Stream.of("edicao")
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
            Class<Revista> clazz = Revista.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscar", String.class),
                        "Deve possuir o método buscar"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularPrecoFinal"),
                        "Deve possuir o método calcularPrecoFinal")
            );
        }
    }

    @Nested
    @DisplayName("3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Revista> clazz = Revista.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Revista> clazz = Revista.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("buscar", String.class));
                metodos.add(clazz.getDeclaredMethod("calcularPrecoFinal"));
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
            Class<Revista> clazz = Revista.class;
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

        @ParameterizedTest(name = "Cenário {index}: titulo=\"{0}\", edicao={3}")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4(String titulo, Integer ano, Double precoBase, Integer edicao)
              throws ReflectiveOperationException {
            Class<Revista> clazz = Revista.class;

            assertDoesNotThrow(() -> {
                clazz.getDeclaredConstructor(String.class, Integer.class, Double.class,
                      Integer.class);
            }, "Construtor com parâmetros deve existir");

            Object obj = clazz.getDeclaredConstructor(String.class, Integer.class, Double.class,
                        Integer.class)
                  .newInstance(titulo, ano, precoBase, edicao);

            ObjectFieldMapping<Revista> mapping = new ObjectFieldMapping<>(Revista.class);

            assertAll(
                  () -> assertEquals(titulo, mapping.get("titulo").get(obj),
                        "Titulo deve ser inicializado corretamente"),
                  () -> assertEquals(ano, mapping.get("ano").get(obj),
                        "Ano deve ser inicializado corretamente"),
                  () -> assertEquals(precoBase, mapping.get("precoBase").get(obj),
                        "PrecoBase deve ser inicializado corretamente"),
                  () -> assertEquals(edicao, mapping.get("edicao").get(obj),
                        "Edicao deve ser inicializado corretamente")
            );
        }
    }

    @Nested
    @DisplayName("4. Herança")
    class HerancaTest {

        @Test
        @DisplayName("1. Revista deve herdar de Item")
        void test1() {
            assertTrue(Item.class.isAssignableFrom(Revista.class),
                  "REvista deve herdar da classe Item");
        }
    }

    @Nested
    @DisplayName("5. Método - buscar")
    class MetodoBuscarTest {

        @DisplayName("Teste 1")
        @ParameterizedTest(name = "Cenário {index}: {0}")
        @ArgumentsSource(BuscarProvider.class)
        void test1(String cenario, Object obj, Object query, Boolean expected)
              throws ReflectiveOperationException {
            Class<Revista> clazz = Revista.class;
            Method method = clazz.getDeclaredMethod("buscar", String.class);

            Object response = method.invoke(obj, (String) query);

            assertEquals(expected, response);
        }
    }

    @Nested
    @DisplayName("6. Método - calcularPrecoFinal")
    class MetodoCalcularPrecoFinalTest {

        @ParameterizedTest(name = "Cenário {index}: precoBase={1} => precoFinal={2}")
        @DisplayName("Deve calcular o preço final com 20% de desconto")
        @ArgumentsSource(CalcularPrecoFinalProvider.class)
        void test1(Object obj, Double precoBase, Double expected) throws ReflectiveOperationException {
            Class<Revista> clazz = Revista.class;
            Method method = clazz.getDeclaredMethod("calcularPrecoFinal");

            Object actual = method.invoke(obj);

            assertEquals(expected, (Double) actual, 0.01);
        }
    }
}
