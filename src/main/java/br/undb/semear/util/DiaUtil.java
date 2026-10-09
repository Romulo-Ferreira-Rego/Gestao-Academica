package br.undb.semear.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

public final class DiaUtil {

    private DiaUtil() {
    }

    public static boolean isDiaUtil(LocalDate data) {
        Objects.requireNonNull(data, "data e obrigatoria");
        DayOfWeek dia = data.getDayOfWeek();
        return dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY;
    }

    public static LocalDate quintoDiaUtilDoMes(YearMonth mes) {
        Objects.requireNonNull(mes, "mes e obrigatorio");
        LocalDate data = mes.atDay(1);
        int uteis = 0;
        while (true) {
            if (isDiaUtil(data)) {
                uteis++;
                if (uteis == 5) {
                    return data;
                }
            }
            data = data.plusDays(1);
        }
    }
}
