package school.sptech;

import java.util.Arrays;

public class ExemploVetor {

    public static void main(String[] args) {

//        Vetor no java é estatico
//        (durante a execucao do progama)

        int[] numeros = new int[8];
        numeros[2] = -1;
        numeros[0] = 69;
        numeros[4] = 67;


        System.out.println(Arrays.toString(numeros));

        Integer[] idades = new Integer[10];
        System.out.println(Arrays.toString(idades));

        Integer[] numerosPreenchidos = new Integer[] {10,20,30};
        System.out.println(Arrays.toString(numerosPreenchidos));

//        iterar == varrer o vetor

        for (int i = 0; i < numerosPreenchidos.length; i++) {
            String mensagem = "Numero preenchido [%d]: %d";
            int valorAtual = numerosPreenchidos[i];
            System.out.println(mensagem.formatted(i,valorAtual));

        }

//        for (int i = 0; i < numerosPreenchidos.length; i++) {
//
//        }
//        numerosPreenchidos.fori

//        for (int i = numerosPreenchidos.length - 1; i >= 0; i--) {
//
//        }
//        numerosPreenchidos.forr

//        enhaced for -> for aprimorado
//        numerosPrenenchidos.for

//        for (Integer tomah : numerosPreenchidos) {
//
//        }

        System.out.println("for aprimorado!");
        for (Integer valorAtual : numerosPreenchidos)
            System.out.println(valorAtual);

//        boolean[] likes = new Boolean[] {true, false, false};
//        String mensagem = likes ? "Deu Like :)" : "Deslike :(";
//        System.out.println(mensagem);



    }
}
