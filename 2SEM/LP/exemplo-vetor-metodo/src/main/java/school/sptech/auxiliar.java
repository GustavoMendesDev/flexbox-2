package school.sptech;

public class auxiliar {

    void dizerBomDia() {

        System.out.println("BOM DIA!!!");
    }

    Double CalcularMedia(Double nota01, Double nota02){
        Double Total = (nota01 + nota02) / 2.0;
        return Total;
    }

    Double CalcularMedia(Double nota01, Double nota02, Double nota03) {
        Double Total = (nota01 + nota02 + nota03) / 3.0;
        System.out.println(Total);
        return Total;
    }

//    Polimorfismo (Muitas formas)
//    sobrecarga :
//    Voce pode criar metodos com mesmo nome
//    desde que o tipo e a quantidade de argumento
//    sejam diferentes

//    void dizerBomDiaa(String nome)
//        System.out.println();


//    void dizerBomDia(String local){
//
    void dizerBomDiab(Integer quantidade) {
            for (int i = 0; i < quantidade; i++) {
//                posso chamar um metodo dentro de outro
                dizerBomDia();
            }
    }

    void validarIdade(Integer idade) {
        if (idade < 0 ) {
            System.out.println("Idade invalida!");
        }
            System.out.println("Idade Valida!");

        }

        Double calcularMedia(Double[] notas){
        Double total = 0.0;
        //notas.for
            for (Double nota : notas) {
                total += nota;
            }
            return total / notas.length;
        }
}

