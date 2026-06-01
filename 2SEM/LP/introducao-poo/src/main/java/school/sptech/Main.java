package school.sptech;

public class Main {
    public static void main(String[] args) {
        // "new" instancia um objeto de Bilhete unico
        // em outras palavras.. cria um bilhete unico

        BilheteUnico B01 = new BilheteUnico();

        B01.numero = "00001";
        B01.titular = "Bob";
        B01.saldo = 0.0;
        B01.isEstudante = true;
        B01.isIdoso = false;
        B01.cor = "Verde";

        B01.printarInformacoes();

        B01.recarregar(100.0);

        B01.passarBilhete();

        B01.printarInformacoes();

    }


    }

