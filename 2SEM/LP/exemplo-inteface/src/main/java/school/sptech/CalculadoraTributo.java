package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraTributo {

    private List<Tributavel> tributaveis = new ArrayList<>();

    public CalculadoraTributo() {
        this.tributaveis = tributaveis;
    }

    public void AdicionarTributavel(Tributavel tributavel) {
        tributaveis.add(tributavel);
    }

    public Double calcularValorTotalTributo() {
        Double valorTributo = 0.0;
        for (Tributavel tributavel : tributaveis) {
            valorTributo += tributavel.getValorTributo();
        }
        return valorTributo;
    }

    public void ExibirTodos() {
        for (Tributavel tributavel : tributaveis) {
            System.out.println(tributavel);
        }
    }

    public void exibirServicos() {
        for (Tributavel tributavel : tributaveis) {
            if (tributavel instanceof Servico) {
                System.out.println(tributavel);
            }
        }

    }
}
