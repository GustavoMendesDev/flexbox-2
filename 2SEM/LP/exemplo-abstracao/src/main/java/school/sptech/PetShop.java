package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class PetShop {

    private String nome;
    private ArrayList <Pet> pets;

    public PetShop(String nome) {
        this.nome = nome;
        this.pets = new ArrayList<>();
    }

    public void ReceberPet(Pet pet) {
        this.pets.add(pet);
    }

    public void AlimentarPet() {
        for (Pet p : pets) {
            System.out.println("=".repeat(10));
            p.Comer();
            p.EmitirSom();
        }
    }

    public List<Cachorro> buscarCachorros() {
        List<Cachorro> cachorros = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet instanceof Cachorro cachorro) {
                cachorros.add(cachorro);
            }
        }
        return cachorros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "PetShop{" +
                "nome='" + nome + '\'' +
                ", pets=" + pets +
                '}';
    }
}

