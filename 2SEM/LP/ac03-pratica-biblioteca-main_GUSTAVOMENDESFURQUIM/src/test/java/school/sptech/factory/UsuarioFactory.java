package school.sptech.factory;

import java.util.ArrayList;
import java.util.List;
import school.sptech.Usuario;
import school.sptech.util.ObjectFieldBuilder;

public class UsuarioFactory {

    public static Object build(String nome) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Usuario.class)
            .with("nome", nome)
            .with("itensEmprestados", new ArrayList<>())
            .build();
    }

    public static Object build(String nome, List<?> itensEmprestados) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Usuario.class)
            .with("nome", nome)
            .with("itensEmprestados", new ArrayList<>(itensEmprestados))
            .build();
    }
}
