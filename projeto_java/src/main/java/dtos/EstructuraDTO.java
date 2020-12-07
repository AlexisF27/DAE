package dtos;

public class EstructuraDTO {
    private String nome;
    private String tipoMaterial;
    private int nb;
    private double LVAo;
    private int q;

    public EstructuraDTO(){

    }

    public EstructuraDTO(String nome, String tipoMaterial, int nb, double LVAo, int q) {
        this.nome = nome;
        this.tipoMaterial = tipoMaterial;
        this.nb = nb;
        this.LVAo = LVAo;
        this.q = q;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public double getLVAo() {
        return LVAo;
    }

    public void setLVAo(double LVAo) {
        this.LVAo = LVAo;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }
}
