package exericicios_datahora.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate d01 = LocalDate.parse("2026-09-12");

        System.out.println("Data formatada: " + fmt1.format(d01));
    }
    
}
