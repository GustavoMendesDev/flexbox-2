package school.sptech;

public enum Naipe {

    OUROS("♠\uFE0F"),
    PAUS("♣\uFE0F"),
    ESPADAS("♥\uFE0F"),
    COPAS("♦\uFE0F");

    private final String unicode;

    Naipe(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }


}
