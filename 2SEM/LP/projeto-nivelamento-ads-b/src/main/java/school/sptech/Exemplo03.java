package school.sptech;

public class Exemplo03 {
    public static void main(String[] args) {
        String nome01 = new String("gustavo");
        String nome02 = new String("gustavo");

        //  USAR "==" NOS TIPOS PRIMITIVOS
        // NAO UTILIZAR "==" PARA TIPOS WRAPPER
        // UTILIZAREMOS O .EQUALS
        //NEGAR .EQUALS = IGUAL

        if (!nome01.equals(nome02)) {
            System.out.println("os nomes sao DIFERENTES!");
        } else {
            System.out.println("Os nomes sao iguais!");
        }

        String nomeBanco = "Bob";
        String nomeDigitado = "bob";

        if(nomeDigitado.equalsIgnoreCase(nomeBanco)){
            System.out.println("Achei o nome!");
        } else{
            System.out.println("Não achei o nome!");
        }
    }

}
