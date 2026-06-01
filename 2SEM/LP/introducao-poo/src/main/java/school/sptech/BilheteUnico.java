package school.sptech;

public class BilheteUnico {

    // Características sāo os atributos
    String numero;
    String titular;
    Double saldo;
    Boolean isEstudante;
    Boolean isIdoso;
    String cor;

    void printarInformacoes() {
        System.out.println("-".repeat(15));
        String mensagem = """
                Numero: %s
                Titular: %s
                Saldo: %.2f
                É Estudante: %b
                É Idoso: %b
                Cor: %s""";
        System.out.println(mensagem.formatted(numero, titular, saldo, isIdoso, isEstudante, cor));
        System.out.println("-".repeat(15));
    }


    Boolean recarregar(Double valor) {
        if (valor == null || valor <= 0) {
            System.out.println("Valor invalido.");
            return false;
        }
        if (valor > 300) {
            System.out.println("o Valor deve ser até R$300,00");
            return false;
        }

        saldo += valor;
        System.out.println("Recarga feita com sucesso :)  " + saldo);
        return true;
    }

    boolean passarBilhete(){
        if (isIdoso){
            System.out.println("Subsidio aplicado :)");
            return true;
        }

        Double precoBase = 5.30;
        Double precoPassagem = isEstudante ? precoBase / 2.0 : precoBase;

        if (saldo < precoBase){
            System.out.println("Saldo invalido :( ");
        }
        saldo = saldo - precoPassagem;
        System.out.println("Vai filhão :)");
        return true;
    }


}
