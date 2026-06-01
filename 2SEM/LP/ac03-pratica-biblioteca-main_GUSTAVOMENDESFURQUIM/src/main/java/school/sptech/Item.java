package school.sptech;

import school.sptech.exception.DadoInvalidoException;

public abstract class Item {
    private final String titulo;
    private final Integer ano;
    private final Double precoBase;

    public Item(String titulo, Integer ano, Double precoBase) {
        if (titulo == null || ano == null || precoBase == null
                || titulo.isBlank() || ano < 1900 || precoBase < 0) {
            throw new DadoInvalidoException("deu errado!");
        }

        this.titulo = titulo;
        this.ano = ano;
        this.precoBase = precoBase;
    }

    public abstract Boolean buscar(String texto);

    public abstract Double calcularPrecoFinal();

    public String getTitulo() {
        return titulo;
    }
    public Integer getAno() {
        return ano;
    }


    public Double getPrecoBase() {
        return precoBase;
    }
}