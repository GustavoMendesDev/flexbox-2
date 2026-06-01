package school.sptech;

// Exceptions checadas
// somos obrigados a trata-las
// se nao tratar da erro de compilacao

import java.io.IOException;

public class CheckedExcepctions {
    public static void main(String[] args) throws IOException {
        Escritor escritor = new Escritor();
        escritor.escrever("vai tricolor!");

        escritor.escrever2(null);
    }
}
