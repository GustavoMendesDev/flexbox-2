package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class ContaCorrente {
    private final String numero;
    private String titular;
    private double saldo;
    private static Integer contador = 0;

    public ContaCorrente(String titular) {
        this.titular = titular;
        this.numero = ThreadLocalRandom.current().nextInt(1_000, 10_000 ) + "";
        this.saldo = 0.0;
        this.contador++;
    }

    public void depositar (Double valor){
        if (valor == null || valor <=0 ) {
            throw new IllegalArgumentException("Valor não pode ser nulo ou menor que zero!");
        }
        this.saldo += valor;
    }

    public void sacar (Double valor){
        if (valor == null || valor <=0 ) {
            throw new IllegalArgumentException("Valor não pode ser nulo ou menor que zero!");
        }
        if (valor > saldo){
            throw new IllegalArgumentException("Saldo insuficiente!");
        }
        this.saldo -= valor;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public static Integer getContador() {
        return contador;
    }

    public static void setContador(Integer contador) {
        ContaCorrente.contador = contador;
    }


    @Override
    public String toString() {
        return "ContaCorrente{" +
                "numero='" + numero + '\'' +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
