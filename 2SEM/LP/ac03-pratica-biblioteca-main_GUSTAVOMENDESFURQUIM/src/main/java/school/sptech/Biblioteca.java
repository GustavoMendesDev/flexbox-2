package school.sptech;

import school.sptech.exception.DadoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private final String nome;
    private final List<Item> itens;
    private final List<Usuario> usuarios;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        if (item == null) {
            throw new DadoInvalidoException("Item inválido");
        }
        this.itens.add(item);
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new DadoInvalidoException("Usuário inválido");
        }
        this.usuarios.add(usuario);
    }

    public void removerItem(String titulo) {
        itens.removeIf(item -> item.getTitulo().equalsIgnoreCase(titulo));
    }

    public List<Item> buscarItens(String texto) {
        List<Item> itensAchados = new ArrayList<>();
        for (Item item : itens) {
            if (item.buscar(texto)) {
                itensAchados.add(item);
            }
        }
        return itensAchados;
    }

    public List<Emprestavel> buscarItensDisponiveis() {
        List<Emprestavel> itensDisponiveis = new ArrayList<>();
        for (Item item : itens) {
            if (item instanceof Emprestavel emprestavel && !emprestavel.estaEmprestado()) {
                itensDisponiveis.add(emprestavel);
            }
        }
        return itensDisponiveis;
    }

    public void emprestar(String titulo, String nomeUsuario) {
        // 1. Procura o usuário pelo nome
        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nomeUsuario)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        // Se não encontrou o usuário, não faz nada
        if (usuarioEncontrado == null) return;

        // 2. Procura o item pelo título (deve ser Emprestavel e não estar emprestado)
        for (Item item : itens) {
            if (item.getTitulo().equalsIgnoreCase(titulo)
                    && item instanceof Emprestavel emprestavel
                    && !emprestavel.estaEmprestado()) {

                emprestavel.emprestar();
                usuarioEncontrado.adicionarEmprestimo(emprestavel);
                return; // encerra após encontrar e emprestar
            }
        }
        // Caso não encontrado, não emprestável ou já emprestado → não faz nada
    }

    public String getNome() {
        return nome;
    }

    public List<Item> getItens() {
        return itens;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }


    }

