package school.sptech;

public class Cachorro  extends  Pet {
    public Cachorro(String nome, Double peso, Integer idade) {
        super(nome, peso, idade);
    }

    @Override
    public void EmitirSom() {
        System.out.println("au au au");
    }

    @Override
    public void Comer() {
        System.out.println("Comendo Osso");
    }

}
