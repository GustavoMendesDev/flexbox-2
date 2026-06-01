package school.sptech;

import java.util.Scanner;

public class ExemploScanner {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        System.out.println("Digite seu nome:");
        String nome = leitor.nextLine();
        System.out.println("Digite o nome do seu nome: " + nome);

        System.out.println("Digite sua nota:");
        Double nota = leitor.nextDouble();
        System.out.println("Digite sua nota do seu nome: " + nota);

        System.out.println("Digite sua Idade:");
        Integer idade = leitor.nextInt();
        System.out.println("Digite sua Idade: " + idade);

        System.out.println("Digite como voce esta:");
        String result = leitor.next();
        System.out.println("Voce esta: " + result);

    }
}
