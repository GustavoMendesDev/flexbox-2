package school.sptech;


public class Revista extends Item{
private final Integer edicao;

    public Revista(String titulo, Integer ano, Double precoBase, Integer edicao) {
        super(titulo, ano, precoBase);
        this.edicao = edicao;
    }




    @Override
    public Boolean buscar(String texto) {
        texto = texto.toLowerCase();
        if (getTitulo().toLowerCase().contains(texto)) {
            return true;
        }
        return false;
    }

    @Override
    public Double calcularPrecoFinal() {
        Double preco = super.getPrecoBase();
        preco = (preco *0.80);
        return preco ;
    }

    public Integer getEdicao() {
        return edicao;
    }
}