package school.sptech;

public class Main {

    public static void main(String[] args) {
        Usuario usuario01 = new Usuario("gugu","gugu@gmail.com","11996634634");
        System.out.println(usuario01);

        Musica musica01 = new Musica("Stargazing","Travis scott",300,"Astroworld");
        System.out.println(musica01);
        Musica musica02 = new Musica("FE!N", "Travis scott", 260,"Utopia");
        System.out.println(musica02);
        Musica musica03 = new Musica("Ela ke leitada","Mc",300,"leitada");
        System.out.println(musica03);

        Playlist playlist01 = new Playlist("PARA O HEXA DO BRASA", usuario01);

        playlist01.adicionarMusica( musica01);
        playlist01.adicionarMusica( musica02);
        playlist01.adicionarMusica( musica03);

        playlist01.calcularDuracaoTotal();

        System.out.println(playlist01);
        System.out.println("Duracao total p1: "
        + playlist01.calcularDuracaoTotal());
    }
}
