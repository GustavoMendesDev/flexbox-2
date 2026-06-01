package school.sptech.provider.usuario;

import java.util.List;
import java.util.stream.Stream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import school.sptech.Emprestavel;
import school.sptech.factory.LivroFactory;
import school.sptech.factory.UsuarioFactory;

public class DevolverEmprestimoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Class<? extends Emprestavel> clazz = new ByteBuddy()
              .subclass(Emprestavel.class)
              .make()
              .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
              .getLoaded();

        Objenesis objenesis = new ObjenesisStd();

        Emprestavel l1 = objenesis.newInstance(clazz);
        Emprestavel l2 = objenesis.newInstance(clazz);
        Emprestavel l3 = objenesis.newInstance(clazz);
        Emprestavel l4 = objenesis.newInstance(clazz);

        Object u1 = UsuarioFactory.build("Alice", List.of(l1));
        Object u2 = UsuarioFactory.build("Bob", List.of(l1, l2));
        Object u3 = UsuarioFactory.build("Carlos", List.of(l2, l3, l4));
        Object u4 = UsuarioFactory.build("Diana", List.of(l3));

        return Stream.of(
            Arguments.of("Remove unico item", u1, l1, List.of()),
            Arguments.of("Remove segundo item", u2, l2, List.of(l1)),
            Arguments.of("Remove item do meio", u3, l3, List.of(l2, l4)),
            Arguments.of("Remove unico item (outro)", u4, l3, List.of())
        );
    }
}
