package exericicios_datahora.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        /*  DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate d01 = LocalDate.parse("2026-09-12");

        System.out.println("Data formatada: " + fmt1.format(d01)); */
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dt = LocalDateTime.parse("28/10/2026 18:45", fmt);

        System.out.println(dt);    // 2026-10-28T18:45
    }

}
