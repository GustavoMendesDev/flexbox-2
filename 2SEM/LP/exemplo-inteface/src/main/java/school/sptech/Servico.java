package school.sptech;

// heranca : extends
// interface: implements
// na heranca so podemos ter na classe no extends
//mna relacao de implements (interface), podemos implementar
// varias classes

public class Servico implements Tributavel, Validavel{

    private String descricao;
    private double valor;

    public Servico(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return "Servico{" + "descricao=" + descricao + ", valor=" + valor + '}';
    }

    @Override
    public Double getValorTributo() {
        return valor * 0.18;
    }

    @Override
    public Boolean Isvalido() {
        return descricao != null;
    }
}
