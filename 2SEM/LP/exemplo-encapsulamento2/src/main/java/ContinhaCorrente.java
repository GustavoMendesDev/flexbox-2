
    public class ContaCorrector {

        String numero;
        String titular;
        Double saldo;
        String telefone;
        String email;

        void Depositar(Double valor){
            if(valor == null || valor <= 0){
                System.out.println("Erro ao depositar");
                return;
            }
            saldo+=valor;
            System.out.println("Depositado com sucesso");
        }
    }

    void Sacar(Double valor){
        if(valor == null || valor <= 0){
            System.out.println("Erro ao sacar");
            return;
        }
        System.out.println("Sacado com sucesso");
        return;
    }

