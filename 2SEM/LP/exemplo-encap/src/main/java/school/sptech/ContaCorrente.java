package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

 public class ContaCorrente {

     private String numero;
     private String titular;
     private Double saldo;
     private String telefone;
     private String email;

    // Construtor: Responsavel por criar/instaciar os projetos
    // Quando nāo criamos um construtor, o Java cria vazio, por padrao

     public ContaCorrente(String titular,  String telefone) {
        this.titular = titular;
        this.telefone = telefone;
        saldo = 0.0;
        numero = ThreadLocalRandom.current().nextInt(1000,2001) + "";
    }

     // Construtor tambem aceita sobrecargas
     public ContaCorrente(String titular, String telefone, String email) {
        this(titular,telefone);
         this.email = email;

     }

     public void Depositar(Double valor){
        if(valor == null || valor <= 0){
            System.out.println("Erro ao depositar");
            return;
        }
        saldo+=valor;
        System.out.println("Depositado com sucesso");
    }

     public void Sacar(Double valor){
        if(valor == null || valor <= 0){
            System.out.println("Erro ao sacar");
            return;
        }
        saldo-=valor;
    }

     //alt + insert
     // GETTERS > METODOS PUBLICOS QUE RETORNA OS VALORES DE ATRIBUTOS PRIVADOS

     public String getTitular() {
         return titular;
     }

     public String getNumero() {
         return numero;
     }

     public String getEmail() {
         return email;
     }

     public Double getSaldo() {
         return saldo;
     }

     public String getTelefone() {
         return telefone;
     }

     public void setTelefone(String telefone) {
         this.telefone = telefone;
     }

 }




