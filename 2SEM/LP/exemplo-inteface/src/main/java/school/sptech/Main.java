package school.sptech;

public class Main {

    public static void main(String[] args) {

        CalculadoraTributo calculadora = new CalculadoraTributo();

        Servico servico = new Servico("pc", 4000);
        Produto produto = new Produto(99999.9,"sofia te amo", "amo minha mulher" );
        Produto produto1 = new Perfume(90.0, "puro voodo","top", "apaixonante");

        calculadora.AdicionarTributavel(servico);
        calculadora.AdicionarTributavel(produto);

        calculadora.ExibirTodos();

        System.out.println("valor total tributable " + calculadora.calcularValorTotalTributo());

        Validador validador = new Validador();
        validador.AdicionarValidavel(servico);

    }
}
