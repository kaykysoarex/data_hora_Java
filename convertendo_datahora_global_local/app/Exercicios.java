package convertendo_datahora_global_local.app;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Exercicios {

    public static void main(String[] args) {
        exercicio1();
        exercicio2();
        exercicio3();
    }

    // Ex. 1: o mesmo Instant (UTC) visto em dois fusos.
    static void exercicio1() {
        Instant i1 = Instant.parse("2026-10-29T18:38:30Z");   // Z = UTC

        // ofInstant(momento, fuso): precisa do Instant E do ZoneId
        LocalDateTime d1 = LocalDateTime.ofInstant(i1, ZoneId.of("America/Sao_Paulo"));   // UTC-3
        LocalDateTime d2 = LocalDateTime.ofInstant(i1, ZoneId.of("Asia/Tokyo"));          // UTC+9

        System.out.println("Fuso 01: " + d1);   // 2026-10-29T15:38:30
        System.out.println("Fuso 02: " + d2);   // 2026-10-30T03:38:30 (já é o dia seguinte)
    }

    // Ex. 2: o fuso pode mudar até o DIA.
    static void exercicio2() {
        Instant i1 = Instant.parse("2026-12-24T01:00:00Z");

        LocalDate d1 = LocalDate.ofInstant(i1, ZoneId.of("America/Sao_Paulo"));   // 01:00 - 3h = 22:00 do dia 23
        LocalDate d2 = LocalDate.ofInstant(i1, ZoneId.of("Europe/Lisbon"));       // inverno: UTC+0, continua dia 24

        System.out.println("data 1: " + d1);   // 2026-12-23
        System.out.println("data 2: " + d2);   // 2026-12-24
    }

    // Ex. 3: systemDefault() usa o fuso da máquina que RODA o código.
    static void exercicio3() {
        Instant i1 = Instant.parse("2026-10-29T18:38:30Z");

        LocalDateTime d2 = LocalDateTime.ofInstant(i1, ZoneId.systemDefault());

        System.out.println("d2: " + d2);   // no Brasil: 2026-10-29T15:38:30
        // Em outra máquina o resultado muda (servidor na nuvem costuma ser UTC).
        // Em sistemas reais, prefira escolher o fuso explicitamente.
    }
}
