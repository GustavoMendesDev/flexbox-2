package school.sptech;

public class Rato extends Pet {

    public Rato(String nome, Double peso, Integer idade) {
        super(nome, peso, idade);
    }

    @Override
    public void EmitirSom() {
        System.out.println("qui qui qui");
    }

    @Override
    public void Comer() {
        System.out.println("qui qui, comendo ração");
    }
}


