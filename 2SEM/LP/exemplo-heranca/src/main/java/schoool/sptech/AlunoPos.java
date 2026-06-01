package schoool.sptech;


public class AlunoPos extends Aluno {

    private Double TCC;

    public AlunoPos(String nome, String ra, Double TCC) {
        super(nome, ra);
        this.TCC = TCC;
    }

    @Override
    public Double calcularMediaFinal() {
        return 0.6 + TCC + 0.1 * getAc01() +
                0.1 * getAc02() + 0.2 * getAc03();
    }

    public Double getTCC() {
        return TCC;
    }

    public void setTCC(Double TCC) {
        this.TCC = TCC;
    }

    @Override
    public String toString() {
        return "AlunoPos{" +
                "TCC=" + TCC +
                "} " + super.toString();
    }
}
