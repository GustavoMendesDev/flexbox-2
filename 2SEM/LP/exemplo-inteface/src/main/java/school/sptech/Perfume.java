package school.sptech;

public class Perfume extends Produto  {

    private String fragrancia;

    public Perfume(Double valor, String nome, String descricao, String apaixonante) {
        super(valor, nome, descricao);
        this.fragrancia = fragrancia;
    }

    public String getFragrancia() {
        return fragrancia;
    }

    public void setFragrancia(String fragrancia) {
        this.fragrancia = fragrancia;
    }

    @Override
    public String toString() {
        return "Perfume{" +
                "fragrancia='" + fragrancia + '\'' +
                "} " + super.toString();
    }

    @Override
    public Double getValorTributo() {
        return super.getValor() * 0.1;
    }
}
