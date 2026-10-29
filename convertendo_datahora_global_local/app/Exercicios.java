package convertendo_datahora_global_local.app;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Exercicios {

    public static void main(String[] args) {
        exercicio1();
        

    }

    static void exercicio1() {
        Instant i1 = Instant.parse("2026-10-29T18:38:30Z");

        LocalDateTime d1 = LocalDateTime.ofInstant(i1, ZoneId.of("America/Sao_Paulo"));
        LocalDateTime d2 = LocalDateTime.ofInstant(i1, ZoneId.of("Asia/Tokyo"));

        System.out.println("Fuso 01: " + d1);
        System.out.println("Fuso 02: " + d2);
    }


}
