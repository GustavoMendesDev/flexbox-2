package schoool.sptech;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {

        Aluno Aluno1 = new Aluno("Gustavo", "777777");
        Aluno1.setAc01(8.7);
        Aluno1.setAc02(6.0);
        Aluno1.setAc03(7.0);

        System.out.println("Media final aluno 1" + Aluno1.calcularMediaFinal());

        AlunoPos AlunoPos1 = new AlunoPos("gui", "777666", 10.00);

        AlunoPos1.setAc01(8.0);
        AlunoPos1.setAc02(5.0);
        AlunoPos1.setAc03(9.1);
        AlunoPos1.setTCC(10.0);

        System.out.println(AlunoPos1);
        System.out.println(AlunoPos1.calcularMediaFinal());

        Faculdade sptech = new Faculdade("SPTECH SCHOOL");
        sptech.maticularAluno(Aluno1);
        sptech.maticularAluno(AlunoPos1);
        System.out.println(AlunoPos1);

        System.out.println("\nLista de Alunos: ");
        sptech.exibirAlunos();

        System.out.println("\nLista de Alunos POS: ");
        sptech.exibirAlunoPos();


        System.out.println("\n Alunos com nota de tcc maior que 5: ");
        sptech.ExibirAlunosComNotaTccMaiorQue(5.0);

        System.out.println("\n a Media da faculdade é: " + sptech.calcularMediaFaculdade());





    }
}
