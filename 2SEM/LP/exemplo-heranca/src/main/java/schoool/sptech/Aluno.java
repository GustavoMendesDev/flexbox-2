package schoool.sptech;

public class Aluno {

    private String nome;
    private String ra;
    private Double ac01;
    private Double ac02;
    private Double ac03;

    public Aluno(String nome, String ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public Double calcularMediaFinal() {
        // 25% ac1 30% ac2 35% ac3
        return 0.25*getAc01() + 0.35*getAc02() + 0.4*getAc03();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public Double getAc03() {
        return ac03;
    }

    public void setAc03(Double ac03) {
        this.ac03 = ac03;
    }

    public Double getAc02() {
        return ac02;
    }

    public void setAc02(Double ac02) {
        this.ac02 = ac02;
    }

    public Double getAc01() {
        return ac01;
    }

    public void setAc01(Double ac01) {
        this.ac01 = ac01;
    }

    @Override
    public String


    toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", ra='" + ra + '\'' +
                ", ac01=" + ac01 +
                ", ac02=" + ac02 +
                ", ac03=" + ac03 +
                '}';
    }
}
