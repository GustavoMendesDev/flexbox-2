package school.sptech;

public interface Emprestavel {

    public String getTitulo();

    public void emprestar();

    public void devolver();

    public Boolean estaEmprestado();
}