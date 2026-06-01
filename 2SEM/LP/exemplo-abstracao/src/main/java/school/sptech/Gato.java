package school.sptech;

public class Gato extends Pet {

    public Gato(String nome, Double peso, Integer idade) {
        super(nome, peso, idade);
    }

    @Override
    public void EmitirSom() {
        System.out.println("MIAUU MIAUUU");
    }

    @Override
    public void Comer() {
        System.out.println("pspsps comendo ração");
    }
}
