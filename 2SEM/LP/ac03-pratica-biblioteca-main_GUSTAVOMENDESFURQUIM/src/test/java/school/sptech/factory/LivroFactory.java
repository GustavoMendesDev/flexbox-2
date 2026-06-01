package school.sptech.factory;

import school.sptech.Livro;
import school.sptech.util.ObjectFieldBuilder;

public class LivroFactory {

    public static Object build(String titulo, Integer ano, Double precoBase, String autor, String genero) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Livro.class)
            .with("titulo", titulo)
            .with("ano", ano)
            .with("precoBase", precoBase)
            .with("autor", autor)
            .withEnum("genero", genero)
            .with("emprestado", false)
            .build();
    }

    public static Object build(String titulo, Integer ano, Double precoBase, String autor, String genero, boolean emprestado) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Livro.class)
            .with("titulo", titulo)
            .with("ano", ano)
            .with("precoBase", precoBase)
            .with("autor", autor)
            .withEnum("genero", genero)
            .with("emprestado", emprestado)
            .build();
    }
}
