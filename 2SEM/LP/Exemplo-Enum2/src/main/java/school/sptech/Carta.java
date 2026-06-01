package school.sptech;

public class Carta {

    private Naipe naipe;
    private Simbolo simbolos;

    public Carta(Naipe naipe, Simbolo simbolos) {
        this.naipe = naipe;
        this.simbolos = simbolos;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public void setNaipe(Naipe naipe) {
        this.naipe = naipe;
    }


    public Simbolo getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(Simbolo simbolos) {
        this.simbolos = simbolos;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "naipe=" + naipe +
                ", simbolos='" + simbolos + '\'' +
                '}';
    }
}
