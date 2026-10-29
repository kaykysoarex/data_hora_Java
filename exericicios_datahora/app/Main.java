package exericicios_datahora.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        /*  DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate d01 = LocalDate.parse("2026-09-12");

        System.out.println("Data formatada: " + fmt1.format(d01)); */
        //-----------------------------------------------------------
        /*   DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime dt = LocalDateTime.parse("28/10/2026 18:45", fmt);

        System.out.println(dt); */    // 2026-10-28T18:45
        //----------------------------------------------------------
        
        DateTimeFormatter fmtEntrada = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter fmtSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime d01 = LocalDateTime.parse("2026/10/28 18:45", fmtEntrada);

        System.out.println(fmtSaida.format(d01));    // 28/10/2026

    }

}
