package school.sptech;

public interface TributavelValidavel extends Tributavel, Validavel {

    @Override
    Double getValorTributo();

    @Override
    Boolean Isvalido();
}
