package school.sptech;

public class Musica {

    private String nome;
    private String artista;
    private Integer duracao;
    private String album;

    public Musica() {
    }

    public Musica(String nome, String artista, Integer duracao, String album) {
        this.nome = nome;
        this.artista = artista;
        this.duracao = duracao;
        this.album = album;
    }

    public String getNome() {
        return nome;
    }

    public String getArtista() {
        return artista;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public String getAlbum() {
        return album;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "nome='" + nome + '\'' +
                ", artista='" + artista + '\'' +
                ", duracao=" + duracao +
                ", album='" + album + '\'' +
                '}';
    }
}
