package school.sptech;

public  class Main {

    public static void main(String[] args) {
        ContaCorrente Conta01 =  new ContaCorrente("bob", "11 999839394454");

        ContaCorrente Conta02 = new ContaCorrente( "gui", "Gui@11999393939.com","gui@gmail.com");

        Conta01.Depositar(200.00);

        Conta02.Depositar(200.00);

        System.out.println(" ");

        System.out.println("Saldo atual: " + Conta01.getSaldo());

        System.out.println(" ");

        System.out.println("Numero: " + Conta01.getNumero());

        Conta02.setTelefone("12321232123212343234212343234");

        System.out.println("Telefone: " + Conta02.getTelefone());

    }

}
