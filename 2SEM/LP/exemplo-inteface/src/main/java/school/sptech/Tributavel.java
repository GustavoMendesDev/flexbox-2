package school.sptech;

// é como se fosse uma classe abstrata apenas com metodos abstratos
public interface Tributavel extends Validavel {

    //o modificador de acesso sera public
    // e os metodos serao abstratos
    // todos atributos da interface soa constantes (static e final)

    public abstract Double getValorTributo();

    @Override
    Boolean Isvalido();
}
