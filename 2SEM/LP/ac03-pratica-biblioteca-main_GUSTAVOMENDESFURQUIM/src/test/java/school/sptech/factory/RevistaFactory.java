package school.sptech.factory;

import school.sptech.Revista;
import school.sptech.util.ObjectFieldBuilder;

public class RevistaFactory {

    public static Object build(String titulo, Integer ano, Double precoBase, Integer edicao) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Revista.class)
            .with("titulo", titulo)
            .with("ano", ano)
            .with("precoBase", precoBase)
            .with("edicao", edicao)
            .build();
    }
}
