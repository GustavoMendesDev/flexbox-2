package school.sptech;

import school.sptech.exception.EmprestavelIndisponivelException;

public class Livro extends Item implements Emprestavel {
    private final String autor;
    private final Genero genero;
    private Boolean emprestado;

    public Livro(String titulo, Integer ano, Double precoBase, String autor, Genero genero) {
        super(titulo, ano, precoBase);
        this.autor = autor;
        this.genero = genero;
        this.emprestado = false;
    }

    public void emprestar() {
        if (emprestado == true) {
            throw new EmprestavelIndisponivelException("ta emprestado pae.");
        } else {
            emprestado = true;
        }
    }

    public void devolver() {
        emprestado = false;
    }

    public Boolean estaEmprestado() {
        return emprestado;
    }


    @Override
    public Boolean buscar(String texto) {
        texto = texto.toLowerCase();
        String textoautor = autor;
        textoautor = textoautor.toLowerCase();

        if (getTitulo().toLowerCase().contains(texto) || textoautor.contains(texto)) {
            return true;
        }
        return false;
    }

    @Override
    public Double calcularPrecoFinal() {
        Double preco = super.getPrecoBase();
        if (getGenero().equals(Genero.BIOGRAFIA)){
            preco = preco * 0.80;
        }
        return preco ;
    }

    public String getAutor() {
        return autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public Boolean getEmprestado() {
        return emprestado;
    }
}




