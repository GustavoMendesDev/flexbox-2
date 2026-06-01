package school.sptech;

public class Main {
    public static void main(String[] args) {

        PetShop petshop = new PetShop("SPPet");

            Pet pet1 = new Cachorro("Thor",32.0,11);
            Pet pet2 = new Gato("Sushi",1.5,2);
            Pet pet3 = new Rato("Miltinho",1.0,2);

            petshop.ReceberPet(pet1);
            petshop.ReceberPet(pet2);
            petshop.ReceberPet(pet3);

            petshop.AlimentarPet();

    }
}
