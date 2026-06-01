package school.sptech;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor {

        public void escrever (String texto){

            try(BufferedWriter writer = new BufferedWriter(
                    new FileWriter("teste.txt")
            );){

        writer.write(texto);
        writer.close();

                System.out.println(texto);
    }catch(IOException variavel){
            System.out.println("Erro na escrita");}
        }

        public void escrever2(String texto) throws IOException{
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("teste.txt"));

            writer.write(texto);
            writer.close();

        }
    }

