package schoool.sptech;

import java.util.ArrayList;
import java.util.List;

public class Faculdade {

    private String nome;
    private List<Aluno> alunos ;


    public Faculdade(String nome) {
        this.nome = nome;
        this.alunos = new ArrayList<>();
    }

    public void maticularAluno (Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void exibirAlunos() {
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    public void exibirAlunoPos() {
        for (Aluno aluno : alunos) {
            if(aluno instanceof AlunoPos) {
                System.out.println(aluno);
            }
        }
    }

    public void ExibirAlunosComNotaTccMaiorQue(Double TCC) {
        for (Aluno aluno : alunos) {
            if(aluno instanceof AlunoPos) {
                AlunoPos alunoPos = (AlunoPos) aluno;
                if (alunoPos.getTCC() > TCC) {
                    System.out.println(alunoPos);
                }
            }
        }
    }

    public Double calcularMediaFaculdade() {
        Double media = 0.0;
        for (Aluno aluno : alunos) {
            media += aluno.calcularMediaFinal();
        }
        Double Media = media / alunos.size();
        return Media;
    }
}
