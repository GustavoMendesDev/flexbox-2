package school.sptech;

public class Main {

    public static void main(String[] args) {
        auxiliar auxiliar = new auxiliar();
        auxiliar.dizerBomDia();
//         auxiliar.dizerBomDiaa();
        auxiliar.dizerBomDiab(5);

        Double ac01 = auxiliar.CalcularMedia(8.0, 5.0);
        Double ac02 = auxiliar.CalcularMedia(8.0, 10.0);
        Double ac03 = auxiliar.CalcularMedia(10.0, 3.0);

//        Double notaFinal = auxiliar.calcularMedia();
//        System.out.println("Nota final:" + notaFinal);

        auxiliar.validarIdade(10);

        Double[] notasAluno = new Double[] {9.0, 5.2, 9.0};
        Double resultadoAluno = auxiliar.calcularMedia(notasAluno);
        System.out.println("media do aluno " + resultadoAluno);

    }
}
