package school.sptech;

public class Exemplo05 {
    public static void main (String[]args){

        Integer num1 = 10;
        Integer num2 = 5;

        System.out.println("Adicao " + (num1 + num2));

        System.out.println("Subtracao " + (num1 - num2));

        System.out.println("Multiplicacao " + (num1 * num2));

        System.out.println("Divisao " + (num1 / num2));

        System.out.println("Resto " + (num1 % num2));

        System.out.println("Potenciacao " + (Math.pow(num1, num2)));

        System.out.println("raiz " + (Math.sqrt(num1)));

        //Inteiro por inteiro, sempre dará um inteiro :)
        Integer numeroA = 5;
        Integer numeroB = 10;

        System.out.println("Divisao: " + (numeroA / numeroB));

        // Uma das variaveis deve ser double
        // ou utilizamos o metodo Double.valueOf()

        Integer numeroA2 = 5;
        Integer numeroB2 = 10;

        System.out.println("Divisao 2: " + (Double.valueOf(numeroA2) / numeroB2));


    }
}
