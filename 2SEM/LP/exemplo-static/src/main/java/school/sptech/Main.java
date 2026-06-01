package school.sptech;

public class Main {
    public static void main(String[] args) {
        ContaCorrente conta01 = new ContaCorrente("Bob");
        conta01.depositar(500.0);
        conta01.sacar(100.0);
        System.out.println(conta01);


        ContaCorrente conta02 = new ContaCorrente("Gusta");
        conta01.depositar(300.0);
        conta01.sacar(100.0);
        System.out.println(conta02);

        System.out.println("contas criadas: " + ContaCorrente.getContador());

        dizerBomdia();

        Double resultado = Math.pow(2,2);
        Double PI = Math.PI;


    }

    public static void dizerBomdia() {
        System.out.println("Bom dia! djjjjj azeitonaaaa");
    }

}


