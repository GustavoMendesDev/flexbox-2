package school.sptech;

public class Calculadora {

    public  double dividir(double a, double b){
        if(b==0){
            // throw: palavra chave para excecoes
            throw new NumeroInvalidoException("CHUPA O BAGULHO!");
        }
        return a/b;
    }
}
