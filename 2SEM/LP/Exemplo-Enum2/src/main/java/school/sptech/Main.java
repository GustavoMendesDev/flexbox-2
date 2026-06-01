package school.sptech;

import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Carta carta01 = new Carta(Naipe.OUROS,Simbolo.NOVE);
        Carta carta02 = new Carta(Naipe.COPAS, Simbolo.NOVE);
        Carta carta03 = new Carta(Naipe.COPAS, Simbolo.SETE);
        Carta carta04 = new Carta(Naipe.PAUS, Simbolo.Q);

        System.out.println(carta01);
        System.out.println(carta02);
        System.out.println(carta03);
        System.out.println(carta04);

        System.out.println( "simbolo de copas é " + Naipe.COPAS.getUnicode() );


        String copasString = Naipe.COPAS.name();

        System.out.println("Copas em String " + Naipe.COPAS.name());
        System.out.println( "Copas em String" + Naipe.COPAS.toString() );

        if (carta01.getSimbolos().compareTo(carta02.getSimbolos()) < 0){
            System.out.println("Menor que zero!");
        }
       else if (carta01.getSimbolos().compareTo(carta02.getSimbolos()) == 0){
            System.out.println("igual a zero!");
        }else {
            System.out.println("maior que zero!");
        }

       Naipe naipeConvertido = Naipe.valueOf("ESPADAS");
        System.out.println("Naipe convertido: " + naipeConvertido);

        System.out.println(" ");

        System.out.println("Crie uma carta!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um Naipe: ");
        String naipeDigitado = scanner.nextLine();

        System.out.println("Digite um Simbolo: ");
        Integer simboloDigitado = scanner.nextInt();

        Naipe naipe = Naipe.valueOf(naipeDigitado.toUpperCase());

        Simbolo simbolo = null;

        Simbolo[] simbolos = Simbolo.values();
        for (Simbolo simboloDaVez : simbolos){
            if (simboloDaVez.getContador().equals(simboloDigitado)){
                simbolo = simboloDaVez;
            }
        }

        Carta carta = new Carta(naipe,simbolo);
        System.out.println(carta);



//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Digite um Naipe: ");
//        String Naipao = scanner.nextLine();
//
//
//        System.out.println("Digite um Simbolo: ");
//        Integer simbolao = scanner.nextInt();
//
//        Simbolo[] simbolo = Simbolo.values();
//        for (Simbolo SimboloDaVez : simbolo) {
//            if (simbolao.equals(SimboloDaVez));
//        }
//
//        Simbolo SimboloConvertido = Simbolo.valueOf(simbolao.toString());
//
//
//        Carta carta05 = new Carta(naipeConvertido, SimboloConvertido);
//        System.out.println(carta05);

    }
}
