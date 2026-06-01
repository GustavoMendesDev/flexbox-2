package school.sptech.factory;

import java.util.ArrayList;
import java.util.List;
import school.sptech.Biblioteca;
import school.sptech.util.ObjectFieldBuilder;

public class BibliotecaFactory {

    public static Object build(String nome, List<?> itens) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Biblioteca.class)
            .with("nome", nome)
            .with("itens", new ArrayList<>(itens))
            .with("usuarios", new ArrayList<>())
            .build();
    }

    public static Object build(String nome, List<?> itens, List<?> usuarios) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Biblioteca.class)
            .with("nome", nome)
            .with("itens", new ArrayList<>(itens))
            .with("usuarios", new ArrayList<>(usuarios))
            .build();
    }
}
