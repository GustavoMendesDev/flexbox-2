package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final String nome;
    private final List<Emprestavel> itensEmprestados;

    public Usuario(String nome) {
        this.nome = nome;
        this.itensEmprestados = new ArrayList<>();
    }

    public void adicionarEmprestimo(Emprestavel emprestavel){
        this.itensEmprestados.add(emprestavel);
    }

    public void devolverEmprestimo(Emprestavel emprestavel){
        this.itensEmprestados.remove(emprestavel);
    }

    public String getNome() {
        return nome;
    }

    public List<Emprestavel> getItensEmprestados() {
        return itensEmprestados;
    }
}