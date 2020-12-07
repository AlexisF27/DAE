package dtos;

public class VarianteDTO {
    private int codigo;
    private String nomeMaterial;
    private String nome;
    private double weff_p;
    private double weff_n;
    private double ar;
    private double sigmaC;
    private double pp;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getWeff_p() {
        return weff_p;
    }

    public void setWeff_p(double weff_p) {
        this.weff_p = weff_p;
    }

    public double getWeff_n() {
        return weff_n;
    }

    public void setWeff_n(double weff_n) {
        this.weff_n = weff_n;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public double getSigmaC() {
        return sigmaC;
    }

    public void setSigmaC(double sigmaC) {
        this.sigmaC = sigmaC;
    }

    public double getPp() {
        return pp;
    }

    public void setPp(double pp) {
        this.pp = pp;
    }
}
