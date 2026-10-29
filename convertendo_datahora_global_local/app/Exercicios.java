package convertendo_datahora_global_local.app;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Exercicios {

    public static void main(String[] args) {
        exercicio1();
        exercicio2();

    }

    static void exercicio1() {
        Instant i1 = Instant.parse("2026-10-29T18:38:30Z");

        LocalDateTime d1 = LocalDateTime.ofInstant(i1, ZoneId.of("America/Sao_Paulo"));
        LocalDateTime d2 = LocalDateTime.ofInstant(i1, ZoneId.of("Asia/Tokyo"));

        System.out.println("Fuso 01: " + d1);
        System.out.println("Fuso 02: " + d2);
    }

    static void exercicio2() {
        Instant i1 = Instant.parse("2026-12-24T01:00:00Z");

        LocalDate d1 = LocalDate.ofInstant(i1, ZoneId.of("America/Sao_Paulo"));
        LocalDate d2 = LocalDate.ofInstant(i1, ZoneId.of("Europe/Lisbon"));

        System.out.println("data 1: " + d1);
        System.out.println("data 2: " + d2);
    }
}
