package school.sptech;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UncheckedExcepections {

    public static void main(String[] args) {
        // Tratar exception: impedir que o progama pare
        // try-catch

        try{
            String nome = null;

            System.out.println("nome é: " + nome.toLowerCase());

            // caso capture a exception
        }
        catch (NullPointerException variavel){
            System.out.println("nome nao pode ser nulo");
            System.out.println("Mensagem da excepction" + variavel.getMessage());

            // caminho do programa ate encontrar exception

            variavel.printStackTrace();
        }

        try{

            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o N1");
            Integer N1 = scanner.nextInt();

            System.out.println("Digite o N2");
            Integer N2 = scanner.nextInt();

            System.out.println("a Divisão é: " + (N1/N2));

            scanner.close();
        }
        catch (InputMismatchException variavel){
            System.out.println("Digite Apenas NUMEROS INTEIROS");
        }
    }
}
