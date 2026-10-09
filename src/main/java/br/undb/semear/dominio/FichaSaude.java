package br.undb.semear.dominio;

public class FichaSaude {

    private boolean possuiDeficiencia;
    private String alergias;
    private String restricaoAlimentar;
    private String acompanhamento;

    public FichaSaude(boolean possuiDeficiencia, String alergias, String restricaoAlimentar, String acompanhamento) {
        this.possuiDeficiencia = possuiDeficiencia;
        this.alergias = alergias;
        this.restricaoAlimentar = restricaoAlimentar;
        this.acompanhamento = acompanhamento;
    }

    public boolean isPossuiDeficiencia() {
        return possuiDeficiencia;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getRestricaoAlimentar() {
        return restricaoAlimentar;
    }

    public String getAcompanhamento() {
        return acompanhamento;
    }

    @Override
    public String toString() {
        return "FichaSaude";
    }
}
