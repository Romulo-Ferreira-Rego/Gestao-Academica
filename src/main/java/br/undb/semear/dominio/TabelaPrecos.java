package br.undb.semear.dominio;

import br.undb.semear.dominio.enums.Serie;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class TabelaPrecos {

    private static final Map<Serie, BigDecimal> VALORES_POR_SERIE;

    static {
        Map<Serie, BigDecimal> valores = new EnumMap<>(Serie.class);
        BigDecimal taxaAssociado = new BigDecimal("125.00");
        valores.put(Serie.MATERNAL_1, taxaAssociado);
        valores.put(Serie.MATERNAL_2, taxaAssociado);
        valores.put(Serie.INFANTIL_1, taxaAssociado);
        valores.put(Serie.INFANTIL_2, taxaAssociado);
        valores.put(Serie.ANO_1, new BigDecimal("310.00"));
        BigDecimal segundoAoQuinto = new BigDecimal("375.00");
        valores.put(Serie.ANO_2, segundoAoQuinto);
        valores.put(Serie.ANO_3, segundoAoQuinto);
        valores.put(Serie.ANO_4, segundoAoQuinto);
        valores.put(Serie.ANO_5, segundoAoQuinto);
        VALORES_POR_SERIE = Collections.unmodifiableMap(valores);
    }

    private TabelaPrecos() {
    }

    public static BigDecimal valor(Serie serie) {
        Objects.requireNonNull(serie, "serie e obrigatoria");
        return VALORES_POR_SERIE.get(serie);
    }
}
