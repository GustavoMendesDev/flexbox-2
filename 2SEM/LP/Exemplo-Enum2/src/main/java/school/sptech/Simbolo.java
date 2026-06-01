package school.sptech;

public enum Simbolo {

    AS(1),
    DOIS(2),
    TRES(3),
    QUATRO(4),
    CINCO(5),
    SEIS(6),
    SETE(7),
    OITO(8),
    NOVE(9),
    DEZ(10),
    Q(11),
    J(12),
    K(13);

    private final Integer Contador;

    Simbolo(Integer contador) {
        Contador = contador;
    }

    public Integer getContador() {
        return Contador;
    }
}
