package br.undb.semear.dominio.enums;

public enum Serie {
    MATERNAL_1(Etapa.EDUCACAO_INFANTIL),
    MATERNAL_2(Etapa.EDUCACAO_INFANTIL),
    INFANTIL_1(Etapa.EDUCACAO_INFANTIL),
    INFANTIL_2(Etapa.EDUCACAO_INFANTIL),
    ANO_1(Etapa.ENSINO_FUNDAMENTAL),
    ANO_2(Etapa.ENSINO_FUNDAMENTAL),
    ANO_3(Etapa.ENSINO_FUNDAMENTAL),
    ANO_4(Etapa.ENSINO_FUNDAMENTAL),
    ANO_5(Etapa.ENSINO_FUNDAMENTAL);

    private final Etapa etapa;

    Serie(Etapa etapa) {
        this.etapa = etapa;
    }

    public Etapa getEtapa() {
        return etapa;
    }
}
