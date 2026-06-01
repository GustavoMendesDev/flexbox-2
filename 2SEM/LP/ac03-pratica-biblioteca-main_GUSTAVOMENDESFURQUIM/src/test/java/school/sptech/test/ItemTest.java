package school.sptech.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Item;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.provider.item.ConstrutorValidacoesProvider;
import school.sptech.provider.item.ConstrutorValidosProvider;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("Item")
public class ItemTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Item> clazz = Item.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("titulo"),
                        "Deve possuir o atributo titulo"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("ano"),
                        "Deve possuir o atributo ano"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("precoBase"),
                        "Deve possuir o atributo precoBase")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() {
            Class<Item> clazz = Item.class;

            Stream<Executable> validacoes = Stream.of("titulo", "ano", "precoBase")
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
            Class<Item> clazz = Item.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscar", String.class),
                        "Deve possuir o método buscar"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularPrecoFinal"),
                        "Deve possuir o método calcularPrecoFinal")
            );
        }

        @Test
        @DisplayName("2. Métodos devem ser abstratos")
        void test2() {
            Class<Item> clazz = Item.class;

            assertAll(
                  () -> assertTrue(Modifier.isAbstract(
                              clazz.getDeclaredMethod("buscar", String.class).getModifiers()),
                        "O método buscar deve ser abstrato"),
                  () -> assertTrue(Modifier.isAbstract(
                              clazz.getDeclaredMethod("calcularPrecoFinal").getModifiers()),
                        "O método calcularPrecoFinal deve ser abstrato")
            );
        }
    }

    @Nested
    @DisplayName("3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Item> clazz = Item.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Item> clazz = Item.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("buscar", String.class));
                metodos.add(clazz.getDeclaredMethod("calcularPrecoFinal"));
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
            Class<Item> clazz = Item.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String getName = String.format("get%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          int getModifier = getter.getModifiers();
                          assertTrue(Modifier.isPublic(getModifier),
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

        @Test
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4() throws ReflectiveOperationException {
            Class<? extends Item> concreteClass = new ByteBuddy()
                  .subclass(Item.class)
                  .make()
                  .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                  .getLoaded();

            assertDoesNotThrow(
                  () -> concreteClass.getDeclaredConstructor(String.class, Integer.class,
                        Double.class),
                  "Construtor com parâmetros deve existir");

            Object obj = concreteClass.getDeclaredConstructor(String.class, Integer.class,
                        Double.class)
                  .newInstance("Item A", 2020, 10.0);

            ObjectFieldMapping<Item> mapping = new ObjectFieldMapping<>(Item.class);

            assertAll(
                  () -> assertEquals("Item A", mapping.get("titulo").get(obj),
                        "Titulo deve ser inicializado corretamente"),
                  () -> assertEquals(2020, mapping.get("ano").get(obj),
                        "Ano deve ser inicializado corretamente"),
                  () -> assertEquals(10.0, mapping.get("precoBase").get(obj),
                        "PrecoBase deve ser inicializado corretamente")
            );
        }

        @Test
        @DisplayName("5. Classe deve ser abstrata")
        void test5() {
            Class<Item> clazz = Item.class;
            assertAll(
                  () -> assertTrue(Modifier.isAbstract(clazz.getModifiers()),
                        "A classe deve ser abstrata"));
        }
    }

    @Nested
    @DisplayName("4. Construtor - Validacoes")
    class ConstrutorValidacoesTest {

        @ParameterizedTest(name = "Cenário {index}: titulo=\"{0}\", ano={1}, precoBase={2}")
        @ArgumentsSource(ConstrutorValidacoesProvider.class)
        @DisplayName("1. Deve lançar DadoInvalidoException para dados invalidos")
        void test1(String titulo, Integer ano, Double precoBase)
              throws ReflectiveOperationException {
            Class<? extends Item> clazz = new ByteBuddy()
                  .subclass(Item.class)
                  .make()
                  .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                  .getLoaded();

            Exception exception = assertThrows(Exception.class,
                  () -> clazz.getDeclaredConstructor(String.class, Integer.class,
                              Double.class)
                        .newInstance(titulo, ano, precoBase));
            assertTrue(DadoInvalidoException.class.isAssignableFrom(
                  exception.getCause().getClass()));
        }

        @ParameterizedTest(name = "Cenário {index}: titulo=\"{0}\", ano={1}, precoBase={2}")
        @ArgumentsSource(ConstrutorValidosProvider.class)
        @DisplayName("2. Não deve lançar DadoInvalidoException para dados validos")
        void test2(String titulo, Integer ano, Double precoBase)
              throws ReflectiveOperationException {
            Class<? extends Item> clazz = new ByteBuddy()
                  .subclass(Item.class)
                  .make()
                  .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                  .getLoaded();

            assertDoesNotThrow(
                  () -> clazz.getDeclaredConstructor(String.class, Integer.class,
                              Double.class)
                        .newInstance(titulo, ano, precoBase));
        }
    }
}
