package calculos_com_datahora.app;

import java.time.LocalDate;

public class Exercicios {

    public static void main(String[] args) {
        exercicio1();
    }

    static void exercicio1() {
        LocalDate d1 = LocalDate.parse("2026-03-15");

        LocalDate diasDepois = d1.plusDays(30);
        LocalDate diasAntes = d1.minusDays(10);

        System.out.println(diasDepois);
        System.out.println(diasAntes);
    }
}
