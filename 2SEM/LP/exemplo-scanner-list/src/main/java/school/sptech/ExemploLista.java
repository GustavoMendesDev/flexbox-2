package school.sptech;

import java.security.UnresolvedPermission;
import java.util.ArrayList;
import java.util.List;

public class ExemploLista {

    public static void main(String[] args) {
// vetor no java é estatico
// String[] nomes = new String[5];
//  List<Integer> lista = new ArrayList<>();
//        lista.add(1);
//        lista.add(2);

        List<String> nomes = new ArrayList<>();
        nomes.add("Joao");
        nomes.add("Jose");
        nomes.add("Maria");
        nomes.add("Bob");
        nomes.add("Gustavo");
        nomes.add("Giovanna");
        nomes.add("Sofia");

        System.out.println(nomes);

        System.out.println("Tamanho da lista " + nomes.size());

        System.out.println("Nome Indice 2: " + nomes.get(6));

        // Remover do indice 3

        nomes.remove("Maria");
        System.out.println(nomes);

        System.out.println("Tamanho da lista " + nomes.size());

        nomes.remove("Joao");
        System.out.println(nomes);

        //Atualizar o indice


        nomes.set(1, "Te amo Sofia");
        System.out.println(nomes);

        //pegar o ultimo da lista
        String ultimoNome = nomes.get(nomes.size() - 1);
        System.out.println(ultimoNome);

        // Lista com valores
        List<String> Frutas = new ArrayList<>(List.of("MELANCIA", "MANGA", "UVA THOMPSON"));
        System.out.println(Frutas);

        Frutas.add("BANANA");
        System.out.println(Frutas);

        // Iterar
        for (int i = 0; i < Frutas.size(); i++) {
            System.out.println(Frutas.get(i));
        }

        // for indo fori
        for (int i = 0; i < Frutas.size(); i++) {
            System.out.println("delicia de " + Frutas.get(i));
        }

        // for voltando forr
        for (int i = Frutas.size() - 1; i >= 0; i--) {

        }

        // for enhanced for (for aprimorado)
        for (String frutaDaVez : Frutas) {
            System.out.println("amo essa fruta " + frutaDaVez);
        }
        ;

        for (int i = 0; i < nomes.size(); i++) {
            String nomeDaVez = nomes.get(i);
            if (nomeDaVez.startsWith("G")) {
                nomes.remove(i);
                i--;
            }
        }
        System.out.println(nomes);

        for (String nomeDaVez : nomes) {
            if (nomeDaVez.startsWith("G")) {
                nomes.remove(nomeDaVez);
            }
        }
        System.out.println(nomes);

        // Dentro do Diamante "<>" so aceita classes!
        List<Integer> numeros = new ArrayList<>();

        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        System.out.println(numeros);

        //Remover 1

        Integer numeroParaRemover = 1;
        numeros.remove(numeroParaRemover);
        System.out.println(numeros);

    }
}
