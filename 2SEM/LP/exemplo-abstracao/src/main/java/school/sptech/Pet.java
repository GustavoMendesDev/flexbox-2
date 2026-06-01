package school.sptech;

import org.w3c.dom.ls.LSOutput;

// abstract é apenas um metodo para voce deixar uma classe de base para criar a sua classe

public abstract class Pet {
    private String nome;
    private Double peso;
    private Integer idade;

    public Pet(String nome, Double peso, Integer idade) {
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
    }

    // Podemos ter tambem metodos abstratos
    //ou seja, representas toda uma ideia
    // 1 so podemos ter metodos abstratos em classes abestratas
    // 2 metodos abstratos nao tem corpo (eu nao sei a implementacao)
    // 3 as classes filhas sao obrigadas a sobreescrever esse metodo

    public abstract void Comer();

    public abstract void EmitirSom();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", peso=" + peso +
                ", idade=" + idade +
                '}';
    }
}
