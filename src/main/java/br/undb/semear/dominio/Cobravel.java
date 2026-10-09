package br.undb.semear.dominio;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Cobravel {

    BigDecimal valorBase();

    BigDecimal valorAPagar(LocalDate dataPagamento);
}
