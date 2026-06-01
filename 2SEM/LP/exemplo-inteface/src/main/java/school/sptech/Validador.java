package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Validador {

    private List<Validavel> Validaveis = new ArrayList<>();

    public void AdicionarValidavel(Validavel Validavel){
        Validaveis.add(Validavel);
    }

    public List<Validavel> getValidaves() {
        return Validaveis;
    }
     public void validar() throws Exception {
        for(Validavel Validavel : Validaveis){
            if(!Validavel.Isvalido()){
                throw new Exception("item esta fudido");
            }
     }
}
