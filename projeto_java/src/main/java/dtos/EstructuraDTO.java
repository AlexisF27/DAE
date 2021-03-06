package dtos;

import entities.TipoEstructura;

public class EstructuraDTO {
    private String nome;
    private TipoEstructura tipoMaterial;
    private int nb;
    private double LVAo;
    private int q;
    private int projetoCode;

    public EstructuraDTO(){

    }


    public EstructuraDTO(String nome, TipoEstructura tipoMaterial, int nb, double LVAo, int q, int projetoCode) {
        this.nome = nome;
        this.tipoMaterial = tipoMaterial;
        this.nb = nb;
        this.LVAo = LVAo;
        this.q = q;
        this.projetoCode = projetoCode;
    }

    public int getProjetoCode() {
        return projetoCode;
    }

    public void setProjetoCode(int projetoCode) {
        this.projetoCode = projetoCode;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEstructura getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoEstructura tipoMaterial) {
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
