package calculos_com_datahora;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {

        LocalDate d04 = LocalDate.parse("2022-07-20");
        LocalDateTime d05 = LocalDateTime.parse("2022-07-20T01:30:26");
        Instant d06 = Instant.parse("2022-07-20T01:30:26Z");

        // ---------------------------------------------------------------
        // 1) DATA-HORA +/- TEMPO
        // Os objetos são IMUTÁVEIS: plus/minus devolvem um objeto NOVO.
        // d04, d05 e d06 continuam com os valores originais.
        // ---------------------------------------------------------------

        // LocalDate e LocalDateTime: métodos prontos (plusDays, minusDays...)
        LocalDate pastWeekDate = d04.minusDays(7);
        LocalDate nextWeekDate = d04.plusDays(7);

        LocalDateTime pastWeekLocalDate = d05.minusDays(7);
        LocalDateTime nextWeekLocalDate = d05.plusDays(7);

        // Instant NÃO tem plusDays/minusDays: usa plus/minus + ChronoUnit.
        // Aceita unidades de tempo fixo (segundos, minutos, horas, dias), mas não meses nem anos.
        Instant pastWeekInstant = d06.minus(7, ChronoUnit.DAYS);
        Instant nextWeekInstant = d06.plus(7, ChronoUnit.DAYS);

        System.out.println("pastWeekDate = " + pastWeekDate);              // 2022-07-13
        System.out.println("nextWeekDate = " + nextWeekDate);              // 2022-07-27

        System.out.println("pastWeekLocalDate = " + pastWeekLocalDate);    // 2022-07-13T01:30:26
        System.out.println("nextWeekLocalDate = " + nextWeekLocalDate);    // 2022-07-27T01:30:26

        System.out.println("pastWeekInstant = " + pastWeekInstant);        // 2022-07-13T01:30:26Z
        System.out.println("nextWeekInstant = " + nextWeekInstant);        // 2022-07-27T01:30:26Z

        // ---------------------------------------------------------------
        // 2) DUAS DATA-HORAS -> DURAÇÃO
        // Duration.between(inicio, fim): fim - inicio
        // ---------------------------------------------------------------

        // LocalDate não tem hora: Duration exige atStartOfDay() (meia-noite daquele dia)
        Duration t1 = Duration.between(pastWeekDate.atStartOfDay(), d04.atStartOfDay());

        // LocalDateTime e Instant funcionam direto
        Duration t2 = Duration.between(pastWeekLocalDate, d05);
        Duration t3 = Duration.between(pastWeekInstant, d06);

        // Ordem invertida (fim antes do início): duração NEGATIVA
        Duration t4 = Duration.between(d06, pastWeekInstant);

        System.out.println("t1 dias = " + t1.toDays());                    // 7
        System.out.println("t2 dias = " + t2.toDays());                    // 7
        System.out.println("t3 dias = " + t3.toDays());                    // 7
        System.out.println("t4 dias = " + t4.toDays());                    // -7

        // Dica: a mesma Duration também dá outras unidades.
        // t1.toHours() -> 168 | t1.toMinutes() -> 10080
    }
}