package school.sptech;

public class Exemplo04 {
    public static void main(String[] args) {

        // Lacos de repeticao
        // sei o numero de vezes que vai interagir - forEach por exemplo
        for (Integer i = 0; i < 10; i++) {
            System.out.println("Numero : " + i);
        }
        int i = 0;
        // Nao sei o numero de interacoes
        while(i < 10){
            i++;
            System.out.println("Fodase - " + i);
        }

        int contador = 0;

        do{
            System.out.println("Contador do while da vez "+ contador);
            contador++;
        }while(contador < 10);
    }
}
