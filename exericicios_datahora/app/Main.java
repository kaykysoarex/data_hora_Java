package exericicios_datahora.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate d01 = LocalDate.parse("2025-03-15");
        LocalDate d02 = LocalDate.of(2025, 03, 15);

        System.out.println("Parse = " + fmt1.format(d01));
        System.out.println("Of = " + fmt1.format(d02));
    }
}
