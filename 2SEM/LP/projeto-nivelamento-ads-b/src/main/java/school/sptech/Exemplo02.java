package school.sptech;
import java.util.Scanner;
public class Exemplo02 {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        // Condicionais: if, else, else if, ternário
        // Comparadores ==, >, <, !=, <=, >=, ===

        Boolean temCarro = false;
        if(temCarro) {
            System.out.println("Tem Ferrari!");
        }

        System.out.println("Digite sua idade");
        Integer idade = ler.nextInt();
        // || (ou) e && (e) mantém


        if (idade < 16){
            System.out.println("Não pode votar!");
        } else if (idade < 18) {
            System.out.println("Pode votar, mas não pode dirigir");
        }else {
            System.out.println("Pode votar e dirigir!");
        }
    }
}
