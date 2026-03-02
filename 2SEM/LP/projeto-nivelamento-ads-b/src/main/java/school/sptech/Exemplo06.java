package school.sptech;

public class Exemplo06 {
    public static void main(String[] args) {
        //INTERPOLACAO
        // VAR MEN = 'EU SOU TOPZEIRA ${Nome}'

        String nome = "Bob";

        Integer idade = 10;

        Float altura = 1.5f;

        Double peso = 45.0;

        Boolean jogaFort = true;

        String mensagem = String.format("Meu nome é: %s, tenho %d anos, tenho " +
                "%.2f de altura e %.2f de peso, Joga fort: %b ",
                nome, idade, altura, peso, jogaFort);
        System.out.println(mensagem);


        String mensagen = ("Meu nome é: %s, tenho %d anos, tenho " +
                "%.2f de altura e %.2f de peso, Joga fort: %b ").formatted(nome, idade, altura, peso, jogaFort);

        System.out.println("\n");

        System.out.printf("meu nome é: %s\n", nome);

        // Ω BLOCO DE TEXTO!!
        String texto = """
                Meu nome é Gustavo
                Eu ainda não gosto de JAVA!
                TMJ \n
                """;

        System.out.println(texto);

        //    "\n" quebra a linha!
    }
}
