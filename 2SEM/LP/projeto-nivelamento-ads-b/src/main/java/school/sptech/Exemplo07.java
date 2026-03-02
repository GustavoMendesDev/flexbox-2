package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class Exemplo07 {
    public static void main(String[] args) {
        // Numero Aleatorias
        //var numeroAletorio = Math.random() * 10 + 10

//        Integer alea = Math.random( )+ 10);

        Integer numeroAleatorio = ThreadLocalRandom.current()
                .nextInt(-1000, -576);
        System.out.println(numeroAleatorio);

        System.out.println("\n");

        Double numeroQuebradoAleatorio = ThreadLocalRandom.current()
                .nextDouble(-10000, -34);

        System.out.println(numeroQuebradoAleatorio);

        System.out.println("\n");

        Boolean booleanAleatorio = ThreadLocalRandom.current()
                .nextBoolean();

        System.out.println(booleanAleatorio);

    }
}
